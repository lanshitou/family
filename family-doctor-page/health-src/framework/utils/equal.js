export function isFunction (fn) {
  if (!fn) {
    return false
  }
  return typeof fn === 'function'
}

export function isString (str) {
  if (!str) {
    return false
  }
  return typeof str === 'string'
}

export function isObject (obj) {
  if (!obj) {
    return false
  }
  return Object.prototype.toString.call(obj) === '[object Object]'
}

export function isPromise (_p) {
  if (
    _p !== null &&
    (typeof _p === 'object' || typeof _p === 'function') &&
    typeof _p.then === 'function'
  ) {
    // 这是一个promise对象
    // console.info('这是一个promise对象')
    return true
  } else {
    return false
  }
}
