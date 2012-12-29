package whaleberg.test

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action

object DrawingWindow extends SimpleSwingApplication {

lazy val ui = new Panel {
		background = Color.white
				preferredSize = (400,200)

				focusable = true
				listenTo(mouse.clicks, mouse.moves, keys)

		reactions += {
		case e: MousePressed  =>
		moveTo(e.point)
		requestFocusInWindow()
		case e: MouseDragged  => lineTo(e.point)
		case e: MouseReleased => lineTo(e.point)
		case e: ButtonClicked => 
		path = new geom.GeneralPath
		repaint()
		case KeyTyped(_,'c',_,_) =>
		path = new geom.GeneralPath
		repaint()
		case _: FocusLost => repaint()
		}

		/* records the dragging */
		var path = new geom.GeneralPath

		def lineTo(p: Point) { path.lineTo(p.x, p.y); repaint() }
		def moveTo(p: Point) { path.moveTo(p.x, p.y); repaint() }

		override def paintComponent(g: Graphics2D) = {
			super.paintComponent(g)
			g.setColor(new Color(100,100,100))
			g.drawString("Press left mouse button and drag to paint." +
					(if(hasFocus) " Press 'c' to clear." else ""), 10, size.height-10)
					g.setColor(Color.black)
					g.draw(path)
		}
	}
  
  
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
					contents += clearButton
					contents += quitButton
		}
	}
}