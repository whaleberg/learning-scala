package whaleberg.test.ui

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import scala.swing.GridPanel
import scala.swing.Panel
import scala.swing.TextArea
import whaleberg.test.PointsPanel

object MapApp extends SimpleSwingApplication {
	
	lazy val panel = new PointsPanel()
	lazy val text = new TextArea{
	  text = "Nothing yet"
	}
  def top = new MainFrame{
	  title = "MapApp Main Frame"
	  contents = new GridPanel(1,2){

		// contents += text;
  		  contents += panel;
		  
	  }
	}
}