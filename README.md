## BTSync-Java

### Purpose
BTSync-Java is a library for the starting and interacting with BitTorrent Sync Beta. To use this library you *must* have received an API Key from BitTorrent Sync which can be done here ***(insert link here)***.  
Bundled with this library is the 1.3.105 version of the BitTorrent Sync Beta binary (32bit) for Windows and Linux.

### Background
This library was created as part of my senior design project, a Universal User Experience system sponsored by HP. My team and I decided to use BitTorrent Sync as our synchronizing agent and needed to be able to start, stop, and interact with BT Sync completely in the background without user interaction.

### Maven Dependency

Add the following dependency to your `pom.xml` to use BTSync-Java

~~~
<dependency>
	<groupId>ms.safi.btsync</groupId>
	<artifactId>btsync-java</artifactId>
	<version>1.0.0</version>
</dependency>
~~~

### Resources

See the wiki for examples on how you can use BTSync-Java within your application.