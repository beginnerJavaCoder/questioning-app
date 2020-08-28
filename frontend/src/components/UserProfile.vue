<template>
  <div>
    <p>WELCOME, {{userInfo.username}}!</p>
  </div>
</template>

<script>
export default {
  name: "UserProfile",
  props: {
    userInfo: {
      token: String,
      userId: Number,
      username: String
    }
  }
}
</script>

<style scoped>

</style>