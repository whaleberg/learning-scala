package whaleberg.test

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action

object DrawingWindow extends SimpleSwingApplication {

lazy val ui = new PathPanel()
lazy val ui2 = new PathPanel()
  
  
	val quitAction = new Action("Quit"){
		def apply() = quit()
	}   

	val clearAction = new Action("Clear"){
	  def apply() = {ui.path = new geom.GeneralPath
			  ui.repaint() 
	  }
	}
	
	lazy val clearButton = new Button {
		action = clearAction

	}
	lazy val quitButton = new Button{
		action = quitAction
	}
	

	
	

	def top = new MainFrame {
		title = "Simple Line Painting Demo"
				contents = new GridPanel(1, 3) {
			hGap = 3
					vGap = 3
					contents += ui
					contents += ui2
					contents += clearButton
					contents += quitButton
		}
	}
}