# EBP 健康风险智能评估系统

社区体检数据的循证风险分层工具。包含两个部分：

| 部分 | 位置 | 用途 |
| --- | --- | --- |
| 网页版 | `android/app/src/main/assets/index.html` | 浏览器直接打开，Mac / Windows / 手机都能用 |
| 安卓 App | `android/` | 把网页版打包成 APK 的 WebView 壳工程 |

---

## 一、Mac 用户怎么用（不需要安装任何东西）

**本仓库里没有需要安装的程序。** 唯一的 `.bat` 文件是 `android/gradlew.bat`，那是
Windows 版的编译脚本，Mac 上不需要、也不能运行。Mac 对应的是同目录下的 `./gradlew`。

### 方式 1：直接打开网页（最简单）

双击仓库根目录的 **`打开网页.command`**，浏览器就会打开评估系统。

> 第一次双击如果被 macOS 拦下，右键点它 → 选「打开」→ 再点一次「打开」即可。
> 也可以直接用浏览器打开 `android/app/src/main/assets/index.html`。

不需要管理员权限，不需要 `sudo`，不需要安装任何运行时。

### 方式 2：发布成网址，手机也能访问

仓库里已经配置好 `.github/workflows/pages.yml`。在 GitHub 上：

1. 打开仓库的 **Settings → Pages**；
2. **Source** 选择 **GitHub Actions**；
3. 到 **Actions → 发布网页版 → Run workflow** 跑一次。

跑完后会得到一个 `https://<用户名>.github.io/ebp-medical/` 网址，
Mac、iPhone、安卓手机用浏览器打开即可，同样不需要安装。

在 iPhone 上还可以用 Safari 的「分享 → 添加到主屏幕」，把它变成一个桌面图标。

---

## 二、怎么拿到安卓 APK（也不需要在 Mac 上装 Android Studio）

编译放在 GitHub 的服务器上完成：

1. 打开仓库的 **Actions** 页签；
2. 左侧选 **构建 Android APK**，点 **Run workflow**；
3. 等几分钟跑完，在这次运行页面底部的 **Artifacts** 里下载 `ebp-medical-debug-apk`；
4. 解压得到 `app-debug.apk`，传到安卓手机上安装。

安卓手机安装时的提示处理：

- 提示「禁止安装未知来源应用」→ 在弹出的设置里，允许你用来打开该文件的 App
  （文件管理器 / 浏览器）「安装未知应用」，再点一次安装即可；
- 提示「安装包解析失败」→ 多半是 APK 没下载完整，重新下载解压一次；
- 系统要求 Android 7.0（API 24）及以上。

> 注意：iPhone **无法**安装 APK。iPhone 请用上面的「方式 2」网页版。

---

## 三、想在本地自己编译（可选，需要 Android Studio）

工程根目录是 **`android/`**，不是仓库根目录 —— 用 Android Studio 打开 `android` 这个文件夹。

```bash
cd android
./gradlew assembleDebug          # Mac / Linux
# gradlew.bat assembleDebug      # Windows
```

产物在 `android/app/build/outputs/apk/debug/app-debug.apk`。

`local.properties`（里面是本机 Android SDK 路径）不再提交到仓库，
Android Studio 打开工程时会自动生成。

---

## 四、AI 报告接口

网页里的 AI 循证报告通过 `window.API_ENDPOINT` 调用后端（当前为
`https://ebp-medical1.vercel.app/api/analyze`）。接口不可用时，页面会自动退回到
本地统计生成的摘要，风险评分和表格不受影响。

App 内可通过右上角「设置」修改接口地址。
