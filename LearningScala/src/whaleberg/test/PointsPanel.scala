package whaleberg.test

import scala.swing.Swing._
import scala.swing.{MainFrame, Panel, SimpleSwingApplication}
import scala.swing.event._
import java.awt.{Color, Dimension, Graphics, Graphics2D, Point, geom}
import scala.swing.Button
import scala.swing.GridPanel
import scala.swing.Action
import java.awt.Rectangle
import java.awt.geom.Rectangle2D
import whaleberg.test.ui.MapApp

class PointsPanel extends Panel{

  	background = Color.white
	preferredSize = (400,200)

	focusable = true
	listenTo(mouse.clicks, mouse.moves)
  	
	reactions += {
 
  	  case e: MouseDragged =>
  	    if(selecting){
  	    	endSelect = e.point
  	    	selected.clear()
  	    	selected.appendAll(getContained(pointsToRect(startSelect, endSelect)))
  	    	MapApp.update()
  	    }
  	  case e: MousePressed =>
	 
		if (e.peer.getButton == java.awt.event.MouseEvent.BUTTON1){
			clicked(e.point)
		} else if (e.peer.getButton == java.awt.event.MouseEvent.BUTTON3){
			selecting = true;
			startSelect = e.point
			endSelect = e.point
			rightClicked(e.point)
		}
		//requestFocusInWindow()
	case e: MouseReleased =>
		  selecting = false;
		  repaint()
		
	case _: FocusLost => repaint()
	}

	def clearDrawing() { 
  	 points.clear()
	  repaint()
	}
	
	val points = MapApp.points
	val selected = MapApp.selection
	var count = 0
	var selecting = false
	var startSelect : Point= null
	var endSelect : Point = null
	
	def clicked(p: Point){ 
	  count += 1
	  points+= p
	  selected.clear()
	  selected.append(p)
	  MapApp.update()
	  repaint()
	}
	

	def getContained(rect :Rectangle):List[Point]={
	  var contained :List[Point] = List()
	  for (p <- points){
		if (rect.contains(p)){
		  contained = p::contained
		}
	  }
	  return contained
	}
	
  	def getClosest(p: Point): Point = {
  	  var close : Point= null
  	  var min = Double.MaxValue
  	  for (p2 <- points){
  	    if ( p.distanceSq(p2) < min){
  	      min = p.distanceSq(p2)
  	      close = p2
  	    }
  	  }
  	  return close 
  	}
	

  	def rightClicked(p: Point){
  	  selected.clear()
  	  selected.append(getClosest(p))
  	  MapApp.update()
  	  repaint()
  	}
  	
	def drawCircle(g: Graphics2D, c : Point, rad : Int) = {
	  g.drawOval(c.getX().toInt-rad, c.getY.toInt -rad, rad*2, rad *2)
	}
	
	def pointsToRect(p1: Point , p2: Point):Rectangle = {
		val x = scala.math.min(p1.x, p2.x)
		val y = scala.math.min(p1.y, p2.y)
		val width = scala.math.abs(p1.x-p2.x)
		val height = scala.math.abs(p1.y-p2.y)
		return new Rectangle(x,y,width, height)
	}

	override def paintComponent(g: Graphics2D) = {
		super.paintComponent(g)
		g.setColor(new Color(100,100,100))
		g.drawString(count.toString, 10, size.height-10)
				g.setColor(Color.black)
		for ( p <- points){
			drawCircle(g, p, 3);
		}
		
		if (selecting) {
		  g.draw(pointsToRect(startSelect, endSelect))
		}
		
		for (p <-selected ){
		
		  drawCircle(g, p, 10)
		}
	}
}