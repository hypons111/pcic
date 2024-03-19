import Mousetrap from 'mousetrap';
import { DirectiveBinding } from 'vue/types/options';
import { VNode } from 'vue';

function preventIEHelp() {
  const isIE = !!window['MSInputMethodContext'] && !!document['documentMode'];
  if (isIE) {
    document['onhelp'] = function () {
      return false;
    };
    window['onhelp'] = function () {
      return false;
    };
  }
}

const bindMousetrap = (el: HTMLElement, bind: DirectiveBinding, vnode: VNode) => {
  const key = bind.value.toLowerCase();
  if (bind.modifiers && bind.modifiers.global) {
    Mousetrap.bindGlobal(key, event => {
      event.preventDefault();
      if (!isDisabled(el)) {
        bindInternal(el, bind, vnode);
      }
    });
  } else {
    Mousetrap.bind(key, event => {
      if (!isDisabled(el)) {
        event.preventDefault();
        bindInternal(el, bind, vnode);
      }
    });
  }
};

function bindInternal(el: HTMLElement, bind: DirectiveBinding, vnode: VNode) {
  // element has shortcut callback
  if (vnode.data.on.shortcut) {
    const evx = new CustomEvent('shortcut', event);
    el.dispatchEvent(evx);
  }
  // element has no shortcut callback use click callback as shortcut callback
  else {
    const clickEvent = new MouseEvent('click');
    el.dispatchEvent(clickEvent);
  }
}

const ShortcutDirective = {
  inserted(el, bind, vnode) {
    preventIEHelp();
    bindMousetrap(el, bind, vnode);
  },
  unbind(el, bind) {
    const key = bind.value.toLowerCase();
    Mousetrap.unbind(key);
  },
};

function isDisabled(element: HTMLElement): boolean {
  if (element instanceof HTMLButtonElement) {
    return (element as HTMLButtonElement).disabled;
  } else {
    if (element.getAttribute('disabled')) {
      return true;
    }
  }
  return false;
}

export { ShortcutDirective };
