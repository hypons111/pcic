<template>
  <div>
    <b-modal
      v-model="dialogIsVisible"
      id="pdf-viewer"
      size="xl"
      title="PDF-Viewer"
      header-bg-variant="secondary"
      header-text-variant="light"
      hide-footer
      content-class="pdf-viewer-100"
      dialog-class="pdf-viewer"
      @close="dialogIsVisible = false"
    >
      <iframe height="100%" width="100%" :src="pdfSrc" />
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'i-pdf-viewer',
  data() {
    return {
      pdfSrc: {
        type: String,
        required: true,
      },
      dialogIsVisible: false,
    };
  },
  methods: {
    isShowDia(parentToChildData, isVisible) {
      if (!parentToChildData) {
        return;
      }
      this.dialogIsVisible = isVisible;
      this.pdfSrc = parentToChildData;
    },
    // 關閉視窗
  },
};
</script>
<style>
/* .modal-dialog {
    max-width: 80%;
    height: 90%;
    display: flex;
} */
.pdf-viewer {
  height: 90%;
}
.pdf-viewer-100 {
  height: 100%;
}
</style>
