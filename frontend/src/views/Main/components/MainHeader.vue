<template>
  <div class="header">
    <div class="blank">
      <div>
        <router-link to="/" class="deco-none">
          <img src="@/assets/logo.png" :width="45" />
        </router-link>
      </div>
      <div style="flex: 1"></div>
      <div>
        <router-link to="/crime" class="deco-none">
          구별 범죄 현황
        </router-link>
      </div>
      <div>
        <router-link to="/safety" class="deco-none">
          우리 동네 안전 지수
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainHeader",
};
</script>

<style scoped>
.header {
  min-width: 1480px;
  background-color: #454d5e;
  height: 59px;
  border-bottom: 1px solid #6d6d6d;
  font-size: 18px;
  color: white;
}
.blank {
  height: 100%;
  margin: 0 auto;
  display: flex;
  align-items: center;
}
.deco-none {
  padding: 0 24px;
}
</style>
