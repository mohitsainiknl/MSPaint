<p align="center">
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img src="/.readme/mspaint-icon/mspaint-icon.png" height="128">
  </a>
  <h1 align="center">MSPaint</h1>
</p>

<p align="center">
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img src="https://img.shields.io/github/license/mohitsainiknl/MSPaint?color=blue">
  </a>
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img alt="" src="https://badgen.net/badge/java/>=v1.8.0/green">
  </a>
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img alt="" src="https://badgen.net/github/release/mohitsainiknl/MSPaint/latest">
  </a>
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img alt="" src="https://badgen.net/github/commits/mohitsainiknl/MSPaint?color=pink">
  </a>
  <a href="https://github.com/mohitsainiknl/MSPaint">
    <img alt="" src="https://img.shields.io/github/stars/mohitsainiknl/MSPaint?color=yellow">
  </a>
</p>

<p align="center">
 Give a :star: if you <b>like it!</b><br>
</p>

![Screenshot of MSPaint](/.readme/mspaint-home-new.png)

## Overview : 
This __MSPaint__ is the upgraded version of the _Microsoft Paint_, and is developed in the Java programming language with the Swing framework (therefore, can be __used on MacOS and Linux__ based operating system). This paint has many more features like - We can edit PNG images __without losing the transparency__ of an image, We can work one Layers of image, and We __can apply filters__ to the image.

MSPaint use `Graphics2D` to draw shapes, by `@overriding` the `paintComponent` function of `JComponent`. Even the buttons are design with `paintComponent` function by extending the `JButton` class in `TButton` class.
```java
public class TButton extends JButton {

	    public TButton(String text) {
        super(text);
        setFocusable(false);
        setMargin(new Insets(3, 5, 3, 5));
        setContentAreaFilled(false);
    }
	    @Override
    public void paintComponent(Graphics g) {

		// drawing customized button here with the help of g

        super.paintComponent(g);	//<--- for print button text
    }
}
```
You can see the basic layout of `MSPaint` by following the link below:<br>
https://github.com/mohitsainiknl/MSPaint/blob/master/.readme/mspaint-layout.png
<br>
<br>

## Features : 
All upgraded features are given below:
<br>

<ul>
  <li>
    <p><b>PNG Support (without loosing transparency)</b><br>
    MSPaint support the `alpha` channel, which make it possitble to work wth layers and editing the png image.
</p>
  </li>
  <li>
<p><b>Layers Support</b> <br>
<img height="64" src=".readme/mspaint-icon/layers_tool.png"/> <br>
Every layer is made up of `JPanel`, which are one-upon another in the `viewport`. We can add Layers and hide them with help of buttons given upon the layers.
  </p>
  </li>
  <li>
<p><b>Filters Support</b> <br>
<img height="64" src=".readme/mspaint-icon/filter_tool.png"/> <br>
Sample of the filters are given in the filters tag. filter settings are also given upon the filters list.  <br>
  <i>For more details follow the link :</i> <br>
http://www.jhlabs.com/ip/filters/index.html
  </p>
  </li>
  <li>
<p><b>Floating Canvas</b> <br>
We can also float the canvas like in _Photoshop_, with the help of button given in view tab.
  </p>
  </li>
</ul>
<br>

__Note :- All the feature are NOT completely implemented yet.__
<br>
<br>
<br>


---
## Technical Details :

### Basic Information :
<ul>
  <li>
     <b>Executable JAR Size : </b>
     <img align="center" src="https://badgen.net/badge/size/17.3MB">
  </li>
  <li>
     <b>GUI Framework : </b>
     <img align="center" src="https://badgen.net/badge/GUI/AWT and Swing">
  </li>
</ul>

### System Requirements :
<ul>
  <li>
     <b>Operating System : </b>
     <img align="center" src="https://badgen.net/badge/OS/Any">
     <sup> * Java is Platform Independent</sup>
  </li>
  <li>
     <b>JRE Version : </b>
     <img align="center" src="https://badgen.net/badge/JDK/>=v1.8.0">
  </li>
  <li>
     <b>Development Environment : </b>
     <img align="center" src="https://badgen.net/badge/Editor/VS Code/"> /
     <img align="center" src="https://badgen.net/badge/IDE/Eclipse/"> /
     <img align="center" src="https://badgen.net/badge/IDE/IntelliJ IDEA/">
  </li>
</ul>

<br>
<br>

---
## Downloads : 
#### 1. Entire Repository
https://github.com/mohitsainiknl/MSPaint/archive/refs/heads/master.zip
<br>
#### 2. JAR File (Executable)
https://github.com/mohitsainiknl/MSPaint/blob/master/publish/MSPaint.jar
<br>
#### 3. .exe File for Windows (not independent, require JRE to run)
https://github.com/mohitsainiknl/MSPaint/blob/master/publish/MSPaint.exe
<br>
<br>
<br>



---
<div align="center">
 <p><b>"Suggestions and project Improvements are Invited!"</b></p>
 <p>Thanks a lot</p>
 <sup>Mohit Saini</sup>
</div>
