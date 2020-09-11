<template>
  <div id="app">
    <NavigationPanel></NavigationPanel>
  </div>
</template>

<script>
import NavigationPanel from './components/NavigationPanel'

export default {
  name: 'App',
  components: {
    NavigationPanel
  }
}
</script>

<style>
#app{
  height: 100%;
}
</style>
