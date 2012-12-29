package whaleberg.test

import swing._

object HelloWorld extends SimpleSwingApplication{
	def top = new MainFrame {
	  title = "Hello, World!"
	    contents = new Button {
		  text = "Click me!"
	  }
	}
}