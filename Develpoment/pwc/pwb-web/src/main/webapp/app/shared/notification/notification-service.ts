import { BButtonClose } from 'bootstrap-vue';

export default class NotificationService {
  private $root;

  private position = 'b-toaster-bottom-right';

  private defaultDelay = 5000;
  private count = 0;

  constructor($root) {
    this.$root = $root;
  }

  info(message: string) {
    this.makeModal(message, 'info');
  }

  danger(message: string) {
    this.makeModal(message, 'danger');
  }

  warning(message: string) {
    this.makeModal(message, 'warning');
  }

  success(message: string) {
    this.makeModal(message, 'info');
  }

  private makeToast(message: string, variant: string, delay: number = this.defaultDelay) {
    const h = this.$root.$createElement;
    const id = `my-toast-${this.count++}`;
    const bvToast = this.$root.$bvToast;

    const messageNode = h('div', [
      message,
      h(BButtonClose, {
        staticClass: 'ml-auto mb-1',
        on: {
          click: () => {
            bvToast.hide(id);
          },
        },
      }),
    ]);

    bvToast.toast(messageNode, {
      id,
      variant,
      toaster: this.position,
      solid: true,
      appendToast: true,
      autoHideDelay: delay,
      noCloseButton: true,
    });
  }

  private makeModal(message: string, variant: string) {
    const bvModal = this.$root.$bvModal;
    bvModal.msgBoxOk(message, {
      headerBgVariant: variant,
      headerTextVariant: 'light',
      titleTag: 'h4',
      title: '系統訊息',
      headerClass: 'p-2 justify-content-center font-weight-bolder',
      bodyClass: 'h5',
      footerClass: 'justify-content-center',
      okTitle: '確定',
    });
  }
}
