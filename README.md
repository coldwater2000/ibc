在IBC的基础上，根据使用中可能会遇到的问题进行了如下修改：
* 增加了开关DownloadOrder，可设置在连接成功后是否下载Open状态订单，默认是false
* 增加开关DelayLogoutMinute、DelayLogoutMinuteIntervalHour，用于设置自动Logout时间向前延时的时间与每次重设的时间间隔，用于解决每日自动logout的问题。ibc每日会根据auto logout设置自动登出，为了解决此问题，本版本设计了自动推延时间的机制，即在固定时间触发，把logout时间设置为当前时间n分钟之前的的时间，即可避免每日重启。但考虑系统稳定性，建议最好每周手动重启一次。

**使用方法：

在config.ini中设置以下几个参数：
* DownloadOrder=false   默认为false，表示在连接成功时不下载Open状态的订单
* DelayLogoutMinute=5   默认为5， 表示设置logout时间为当前时间之前5分钟的时间，如当前为12:00,则会设置为11:55。如果有这个设置，则会在首次IBC启动IBGateway时进行一次设置，如设置了DelayLogoutMinuteIntervalHour则会重复执行；
* DelayLogoutMinuteIntervalHour=10  默认为10，表示每间隔10个小时进行一次DelayLogoutMinute设置，避免IBGateway重启。


**Download the
[latest official release here](https://github.com/IbcAlpha/IBC/releases/latest)**

IBC automates many aspects of running [Interactive Brokers](https://www.interactivebrokers.com) [Trader Workstation and Gateway](https://www.interactivebrokers.com/en/index.php?f=14099#tws-software) 
that would otherwise involve manual intervention. It's especially useful for 
those who run automated trading systems based on the [Interactive Brokers API](http://interactivebrokers.github.io), 
but many manual traders find it helpful as well.

Here are some of the things it will do for you:

* It automatically fills in your username and password in the Login 
dialog when TWS or Gateway start running, and clicks the Login button
* It handles various dialog boxes which TWS sometimes displays, to keep 
things running smoothly with no user involvement
* It can keep TWS running indefinitely if required, by dealing with the 
autologoff mechanism (however this mechanism no longer works for TWS 974 
and later version because Interactive Brokers have changed TWS functionality 
to prevent it)
* It allows TWS to be shut down at a specified time on a specified day 
of the week (again, this functionality no longer applies for TWS 974 
and later versions)
* It allows the Gateway to be shut down at a specified time every day
* It can be remotely instructed to shut down TWS or Gateway, which can
be useful if they are running in the cloud or on an inaccessible computer

IBC runs on Windows, macOS and Linux.

> IMPORTANT NOTICES
>
> Please note that IBC cannot automatically complete your login if 
Interactive Brokers have given you a card or device that you must use 
during login. IBC can still enter your username and password, but you 
will have to type in the relevant code and complete the login. You can 
request Interactive Brokers (via your Account Management page on their 
website) to relax this requirement when logging in to TWS or Gateway, 
but you will lose certain guarantees should you suffer losses as a 
result of your account being compromised.
>
> If you're moving to IBC from IBController, there are some changes 
that you'll have to make. See the [IBC User Guide](userguide.md) for 
further information. 


Downloads
---------

If you just want to use IBC without modifying it, you should download 
the latest official release ZIP which you can find 
[here](https://github.com/IbcAlpha/IBC/releases/latest). Note that
there are separate release files for Windows, macOS and Linux.

Users who want to make changes to IBC should clone this repository
in the usual way.

User Guide
----------

Please see the [IBC User Guide](userguide.md) for installation and
usage instructions. The User Guide is also included as a PDF file in the 
download ZIPs.

Support
-------

> IMPORTANT
> By far the most common problem that users have when setting up IBC
is the result of trying to use it with the self-updating version of TWS.
>
>**IBC DOES NOT WORK with the self-updating version of TWS.**
>
>You must install the offline version of TWS for use with IBC.
>
>Note however that there is no self-updating version of the Gateway, so the
normal Gateway installer will work fine if you only want to use the Gateway.

If you need assistance with running IBC, or have any queries or suggestions 
for improvement, you should join the [IBC User Group](https://groups.io/g/ibcalpha).

If you're convinced you've found a bug in IBC, please report it via either 
the 
[IBC User Group](https://groups.io/g/ibcalpha) or the 
[GitHub Issue Tracker](https://github.com/IbcAlpha/IBC/issues).
Please provide as much evidence as you can, especially the versions of IBC 
and TWS/Gateway you're using and a full description of the incorrect 
behaviour you're seeing. 

Note that IBC creates a log file that records a lot of useful information 
that can be very helpful in diagnosing users' problems. The location of 
this log file is prominently displayed in the window that appears when you 
run IBC. It is helpful to attach this log file to any problem reports.

Contributing
------------

There are several ways you may be able to contribute to IBC's ongoing 
development and support. Please read the
[contributor guidelines](CONTRIBUTING.md), and send us a 
[pull request](../../pulls).

We also thank past contributors to the IBController project from which 
IBC was forked: Richard King, Steven Kearns, Ken Geis, Ben Alex and 
Shane Castle.

License
-------

IBC is licensed under the
[GNU General Public License](http://www.gnu.org/licenses/gpl.html) version 3.

History
-------

A brief note by Richard L King (rlktradewright on GitHub) updated 
5 April 2019.

IBC is a fork of the original 
[IBController project](https://github.com/ib-controller/ib-controller). 
For many years, from 2004 to early 2018, I was the primary 
maintainer, developer and supporter for that project. 

For reasons beyond my control, in early 2018 I decided to withdraw my direct
support for the original project, and to create this fork. It is my intention
to ensure that this fork continues to be developed and supported to the high
standards of the past.

The status of the original IBController repository now seems unclear, so
IBController users are invited to switch to IBC.

If you switch from IBController to IBC, please note that there are some 
significant differences, and it's best to install IBC from scratch using 
the download on the [Releases page](https://github.com/IbcAlpha/IBC/releases). 
The last section of the [IBC User Guide](userguide.md) contains useful 
information about these differences.



