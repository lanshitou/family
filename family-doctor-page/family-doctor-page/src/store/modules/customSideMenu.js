const state = {
  menuItem: {}
}
const getters = {}
const actions = {
  updateMenuItem ({commit, state}, k) {
    commit('UPDATE_MENU_ITEM', k)
  }
}
const mutations = {
  UPDATE_MENU_ITEM (state, k) {
    state.menuItem = k
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
