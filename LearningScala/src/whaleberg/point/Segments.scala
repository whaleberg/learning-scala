package whaleberg.point

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action



object Segments extends SimpleSwingApplication {


	def top = new MainFrame{
		title = "MapApp Main Frame"
				contents = new Panel(){
			background = Color.white
					preferredSize = (400 ,200)

					focusable = true
					listenTo(mouse.clicks, mouse.moves)


			override def paintComponent(g: Graphics2D) = {
				super.paintComponent(g)
				g.setColor(new Color(100,100,100))
				g.drawLine(0, 0, 10, 10)
			}


		};
	}

}