// POLYFILLS
// Event.composedPath
// Possibly normalize to add window to Safari's chain, as it does not?
// https://gist.github.com/rockinghelvetica/00b9f7b5c97a16d3de75ba99192ff05c
(function (E, d, w) {
  if (!E.composedPath) {
    E.composedPath = function () {
      if (this.path) {
        return this.path;
      }
      var target = this.target;

      this.path = [];
      while (target.parentNode !== null) {
        this.path.push(target);
        target = target.parentNode;
      }
      this.path.push(d, w);
      return this.path;
    };
  }
})(Event.prototype, document, window);
