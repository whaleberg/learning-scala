package whaleberg.test

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action
import scala.swing.Label

object DrawingWindow extends SimpleSwingApplication {

lazy val ui = new PathPanel()
lazy val ui2 = new PathPanel()
  
  
	val quitAction = new Action("Quit"){
		def apply() = quit()
	}   

	def clearPathPanel(p : PathPanel) = {
	  p.path = new geom.GeneralPath
	  p.repaint()
	}

	val clearAction = new Action("Clear All"){
	  def apply() = {
	    clearPathPanel(ui)
	    clearPathPanel(ui2)
	  }
	}
	
	lazy val clearButton = new Button {
		action = clearAction

	}
	lazy val quitButton = new Button{
		action = quitAction
	}
		
	lazy val infoLabel = new Label("Default")
	
	def top = new MainFrame {
		title = "Simple Line Painting Demo"
		contents = new GridPanel(2, 3) {
			hGap = 3	
			vGap = 3
			contents += ui
			contents += ui2
			contents += clearButton
			contents += quitButton
			contents += infoLabel
		}

	}
}