package whaleberg.test.ui

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import scala.swing.GridPanel
import scala.swing.Panel
import scala.swing.TextArea
import scala.swing.TextField
import whaleberg.test.PointsPanel
import scala.swing.Label
import scala.swing.ListView
import scala.collection.mutable.ArraySeq
import scala.collection.mutable.ArrayBuffer
import java.awt.Point
import scala.collection.mutable.Buffer
import scala.swing.event.ListSelectionChanged
import scala.swing.event.KeyPressed
import scala.swing.event.KeyPressed
import scala.swing.event.FocusLost
import scala.swing.event.KeyTyped
import scala.swing.event.Key
import scala.swing.ScrollPane

object MapApp extends SimpleSwingApplication {
	
	lazy val panel = new PointsPanel()
	lazy val text = new TextArea{
	  text = "Nothing yet"
	}
	val fieldx = new TextField("1")
	val fieldy = new TextField("2")
	
	val points : Buffer[Point]= new ArrayBuffer()
	val selection :Buffer[Point] = new ArrayBuffer()
	
	lazy val subpanel = new GridPanel(2,1){
	    contents+=fieldx
	    contents+= fieldy
	  
	}
	
	val pointsView :ListView[Point] = new ListView( points){
	  focusable = false
	}
	

	
	def update() = {
	  pointsView.listData = points;
	
//	  val inds = selection.map((p:Point) => points.indexOf(p))
//	  pointsView.selectIndices(inds:_* )

	  pointsView.repaint()

	}
	  
	
  def top = new MainFrame{
	  title = "MapApp Main Frame"
	   contents = new GridPanel(2,1){

	    listenTo(pointsView.selection, this.keys)
	    reactions+={
	      case ListSelectionChanged(_,_,_) => 
		      selection.clear()
		      selection.appendAll( pointsView.selection.items)
		      repaint()
		    
	      case KeyPressed(_, Key.Delete, _, _) => 
	        points --= selection
	        selection.clear()
	        repaint()
	      
	     }
	     focusable = true
         requestFocus()
		// contents += text;
  		  contents += panel
  		  //contents += subpanel
  		  contents += new ScrollPane( pointsView)
		  
	  }
	  
	}
}