
const remove = function (arr, item) {
  if (!arr.length) return
  var index = arr.indexOf(item)
  if (index > -1) {
    let rstarr = arr.splice(index, 1)
    return rstarr
  }
}

export default remove
