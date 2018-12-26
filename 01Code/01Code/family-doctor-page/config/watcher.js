console.log('Watching dirs...')
const { resolve } = require('path')
const chokidar = require('chokidar')
const fs = require('fs')
const routeList = []

const watcher = chokidar.watch(resolve(__dirname, '../src/framework/scss'), {
  ignored: /(^|[\/\\])\../
})

watcher
  // 监听目录添加
  .on('add', path => {
    let routeName = path.split('/').pop()
    let _scssPath = '@import "' + routeName + '";'
    var buf = new Buffer(1024)

    // console.log('Going to open an existing file')
    fs.open(
      resolve(__dirname, '../src/framework/scss/index.scss'),
      'r+',
      function (err, fd) {
        if (err) {
          return console.error(err)
        }
        // console.log('File opened successfully!')
        fs.read(fd, buf, 0, buf.length, 0, function (err, bytes) {
          if (err) {
            console.log(err)
          }
          if (bytes > 0) {
            let str = buf.slice(0, bytes).toString()
            if (str.indexOf(_scssPath) < 0 && routeName !== 'index.scss') {
              _scssPath = '\n' + _scssPath
              fs.appendFile(
                resolve(__dirname, '../src/framework/scss/index.scss'),
                _scssPath,
                function (err) {
                  if (err) {
                    console.info('scss目录下的index.scss写入失败！')
                  } else {
                    console.info('scss目录下的index.scss更新成功')
                  }
                }
              )
            }
          }
        })
      }
    )
  })
  .on('unlink', path => {
    let routeName = path.split('/').pop()
    let _scssPath = '@import "' + routeName + '";'
    var buf = new Buffer(1024)

    console.log('Going to open an existing file')
    fs.open(
      resolve(__dirname, '../src/framework/scss/index.scss'),
      'r+',
      function (err, fd) {
        if (err) {
          return console.error(err)
        }
        // console.log('File opened successfully!')
        fs.read(fd, buf, 0, buf.length, 0, function (err, bytes) {
          if (err) {
            console.log(err)
          }
          if (bytes > 0) {
            let str = buf.slice(0, bytes).toString()
            str = str.replace('\n' + _scssPath, '')
            // if (str.indexOf(_scssPath) < 0 && routeName !== 'index.scss') {
            //   _scssPath = '\n' + _scssPath
            fs.writeFile(
              resolve(__dirname, '../src/framework/scss/index.scss'),
              str,
              function (err) {
                if (err) {
                  console.info('scss目录index.scss更新失败！')
                } else {
                  console.info('scss目录index.scss更新成功')
                }
              }
            )
          }
        })
      }
    )
  })

module.exports = watcher
