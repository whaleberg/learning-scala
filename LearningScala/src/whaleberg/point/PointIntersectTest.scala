package whaleberg.point


import whaleberg.point.Segment
import whaleberg.point.Point
import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action
import scala.collection.mutable.ArrayBuffer
import scala.util.Random


object PointIntersectTest extends SimpleSwingApplication {
	var segs1: List[Segment] = Nil
			var segs2: List[Segment] = Nil
			val range = new Range(10, 50, 10)
for(i <- range){
	segs1 = new Segment(new Point(0,0), new Point(100, i*10))::segs1
			segs2 = new Segment(new Point(0,100), new Point(100,i*10))::segs2
}

def randomPoint (xBound:Int, yBound:Int):Point = {
		val x = Random.nextInt(xBound)
				val y = Random.nextInt(yBound)
				new Point(x,y)
}
val segs = segs1.zip(segs2)

def drawSegment(seg: Segment, g: Graphics2D){
	val start = seg.start
			val end = seg.end
			g.drawLine(start.x.toInt, start.y.toInt, end.x.toInt, end.y.toInt)
}

def highlightPoint(p :Point, g: Graphics2D){
	val oldColor = g.getColor()
			g.setColor(Color.RED);
	g.drawOval(p.x.toInt, p.y.toInt, 1, 1)
	g.drawOval(p.x.toInt-2, p.y.toInt -2, 4, 4)
	g.setColor(oldColor)
}


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
			for ((s1, s2) <- segs){
				s1.intersects(s2) match {
				case Intersect(p) => highlightPoint(p, g )
				case _ => ()
				}

				drawSegment(s1, g)
				drawSegment(s2, g)
			}
		}
	}


}
}


