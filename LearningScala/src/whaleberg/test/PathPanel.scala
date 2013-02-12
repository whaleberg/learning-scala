package whaleberg.test

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action

class PathPanel extends Panel {

	background = Color.white

	preferredSize = (600,200)

			focusable = true
			listenTo(mouse.clicks, mouse.moves)

	reactions += {
	case e: MousePressed  =>
	moveTo(e.point)
	requestFocusInWindow()
	case e: MouseDragged  => lineTo(e.point)
	case e: MouseReleased => lineTo(e.point)
	case KeyTyped(_,'c',_,_) =>
	path = new geom.GeneralPath
	repaint()
	case _: FocusLost => repaint()
	}

	def clearDrawing() {
		path = new geom.GeneralPath()
		repaint()
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
