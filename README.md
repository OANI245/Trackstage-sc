# Trackstage

Trackstage是一个正在开发中的项目，基于ANTE，添加了一些功能并优化了GUI逻辑。<br/>
Aphrodite's Nemo's Transit Expansion (MTR-ANTE) 是一个基于Minecraft Transit Railway Mod的实验性功能扩展，基于MTR-NTE。

## 支持

[文档](https://aphrodite281.github.io/mtr-ante/#/)、QQ群(836291719 ANTE)、QQ群(834177457 TS)

## 下载
[Releases](https://github.com/aphrodite281/mtr-ante/releases) 、 [Modrinth](https://modrinth.com/mod/mtr-ante/)

## 构建

在执行`build`任务前须先执行`setupLibrary`，可以用类似-PbuildVersion="`<version>`"参数指定版本(1.17.1 1.18.2 1.19.4 1.20.1 1.21.1)，默认为1.20.1。
以1.21.1为例，  
第一次构建前执行：

- `./gradlew setupLibrary -PbuildVersion="1.21.1"`

每次构建时执行：

- `./gradlew build -PbuildVersion="1.21.1"`  

## Q&A
#### 为什么要制作这个模组？
MTR4发布已有近三年时间，但因为其层出不穷的缺点（掉帧、数据不传递等问题）导致一直不能替代MTR3，MTR3的1.20.1版本问题较多，且1.20.1也已发布2年6月之久，为了能够跟进新版Minecraft，Berries开发组决定分支ANTE开发Trackstage模组。

#### 支持Forge吗？
本模组不支持Forge，未来考虑支持NeoForge。

#### 支持1.16.5~1.19.2版本吗？
因为没有人使用MTR4不是因为需要使用更新的Minecraft版本，因此不考虑支持。

#### 支持1.21以上版本吗？
目前MTR4暂未支持这些Minecraft版本，MTR模组支持后此模组会跟进。

#### 支持MTR3吗？
用你的ANTE去。