// states
const state = {
  orgdatas: true,
  choose: {
    treeId: {
      orgTree: ''
    },
    current: {
      id: '',
      type: '',
      url: ''
    }
  }
}

// getters
const getters = {
  getTableCurrentId: state => state.choose.current.id,
  getTableCurrentType: state => state.choose.current.type,
  getTableCurrent: state => state.choose.current,
  orgdatas: state => state.orgdatas
}

// actions
const actions = {
  chooseCurrent ({commit, state}, chooseInfo) {
    commit('CHOOSE_CURRENT', chooseInfo)
  },
  updateModal ({commit, state}, b) {
    commit('UPDATE_MODAL', b)
  },
  updateCommonModal ({commit, state}, c) {
    commit('UPDATE_COMMON_MODAL', c)
  },
  updateTreeId ({commit, state}, d) {
    commit('UPDATE_TREE', d)
  },
  updateOrgDatas ({commit, state}) {
    commit('UPDATE_ORG_DATAS')
  }
}

// mutations
const mutations = {
  CHOOSE_CURRENT (state, chooseInfo) {
    if (chooseInfo.modalname !== undefined && chooseInfo.modalname !== null) {
      state.choose.allmodal[chooseInfo.modalname] = chooseInfo
    }
    Object.assign(state.choose.current, chooseInfo)
  },
  UPDATE_MODAL (state, b) {
    state.choose.modal = b
  },
  UPDATE_COMMON_MODAL (state, c) {
    state.choose[c.name] = c.val
  },
  UPDATE_TREE (state, d) {
    state.choose.treeId[d.under] = d.val
  },
  UPDATE_ORG_DATAS (state) {
    state.orgdatas = !state.orgdatas
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
