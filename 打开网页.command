#!/bin/sh
# macOS 专用：双击本文件，即可用默认浏览器打开 EBP 网页版。
# 这是 Windows 上 .bat 文件的 Mac 对应版本，不需要管理员权限，不需要安装任何东西。
DIR=$(cd "$(dirname "$0")" && pwd)
open "$DIR/android/app/src/main/assets/index.html"
