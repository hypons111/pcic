<template>
  <div>
    <footer>
      <!--    版權所有 &copy; 中華民國 {{ currentMinguoYear }} 年，行政院公共工程委員會-->
      <div class="container">
        <div class="row justify-content-xl-between justify-content-center align-items-center">
          <div class="col col-xl-auto col-md-12 col-12">
            <div class="row">
              <div class="col col-xl-auto col-lg-auto col-md-6 col-12 text-center">
                <router-link to="/" class="navbar-brand">
                  <img src="../../../content/images/custom/footer_logo.png" height="52px" class="d-inline-block align-top" alt="" />
                </router-link>
              </div>
              <div class="col col-xl-auto col-lg-auto col-md-6 col-12">
                <ul>
                  <li>地址：11010臺北市信義區松仁路3號9樓</li>
                  <li>電話：(02)8789-7500</li>
                  <li>傳真：(02)8789-7800</li>
                </ul>
              </div>
            </div>
            <div></div>
          </div>

          <div class="contact-block col col-xl-6 col-lg-11 col-11">
            <div class="container">
              <div class="row contact-block-div text-center">
                <div class="col col-md-3 col-12">
                  <span class="d-block">全民督工服務專線</span>
                  <span>0800-009-609</span>
                </div>
                <div class="col col-md-3 ol-12">
                  <span class="d-block">採購稽核檢舉專線</span>
                  <span>(02)8789-7548</span>
                </div>
                <div class="col col-md-3 col-12">
                  <span class="d-block">採購爭議處理專線</span>
                  <span>(02)8789-7530</span>
                </div>
                <div class="col col-md-3 col-12">
                  <span class="d-block">閒置通報專線</span>
                  <span>0800-085-854</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <div class="footer-info">
      <span>瀏覽人次：96169267</span>
      <span class="d-none d-md-block">最佳瀏覽狀態為IE11以上、Firefox及Chrome瀏覽器，1024 x 768以上解析度最佳</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppFooter',
  setup() {
    return {};
  },
};
</script>
<style scoped>
footer {
  font-size: 14px;
  color: #fff;
  background-color: #1aa4b7;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
}

footer ul {
  margin: 0;
  list-style: none;
  padding-left: 1rem;
}

footer ul li {
  text-align: left;
}

footer .contact-block {
  color: #4a4a4a;
  background: rgba(255, 255, 255, 0.75);
  margin-top: 1rem;
  margin-bottom: 1rem;
  text-align: left;
}

footer .contact-block-div div {
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.footer-info {
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: var(--medium-grey);
  font-weight: 500;
  letter-spacing: 0.05rem;
  padding: 0.5rem 0.75rem;
}
</style>
