package whaleberg.test

import scala.swing._
import scala.swing.event._
import scala.actors._

//From http://www.learn-scala.com/?p=132
//Mark Nelson, Ali Mukadam

object example extends SimpleSwingApplication {

	// define the main UI window
	def top = new MainFrame {
		// create a reference for the other window
		val other = otherWindow
				// start the logger in another thread (pool)
				log.start()

				// set up window contents
				title = "Main Window"
				size = new Dimension(300,200)
		preferredSize = new Dimension(300,200)
		minimumSize = new Dimension(300,200)
		val button = new Button {
			text = "Open other window"
		}
		val label1 = new Label {
			text = "This is the main window."
		}
		val label2 = new Label {
			text = "App will end if I am closed."
		}
		contents = new BoxPanel(Orientation.Vertical) {
			contents += button
					contents += label1
					contents += label2
					border = Swing.EmptyBorder(30, 30, 10, 30)
		}

		// build menus
		menuBar = new MenuBar
				// (F)ile
				val fileMenu = new Menu("File")
		fileMenu.mnemonic = Key.F
		// (F)ile -&gt; (O)pen
		val openItem = new MenuItem(Action("Open other window") {
			other.visible = true
					log ! "Menu item selected"
		})
		openItem.mnemonic = Key.O
		fileMenu.contents += openItem
		// (F)ile -&gt; E(x)it
		val exitItem = new MenuItem(Action("Exit") {
			dispose()
			System.exit(0)
		})
		exitItem.mnemonic = Key.X
		fileMenu.contents += exitItem
		menuBar.contents += fileMenu

		// set up listeners
		listenTo(button)
		reactions += {
		case ButtonClicked(b) =>
		println("[UI  on " + Thread.currentThread + "]")
		other.visible = true
		log ! "Button clicked in main window"
		}
		println("[UI  on " + Thread.currentThread + "]")

		// tell the logger to report that the UI is started
		log ! "UI started"
	}

	// define the other window, which can be opened from
	// the main window when desired.
	def otherWindow = new Frame {
		// set up window contents
		title = "Other Window"
				size = new Dimension(300,200)
		preferredSize = new Dimension(300,200)
		minimumSize = new Dimension(300,200)
		location = new Point(400,100)
		val button = new Button {
			text = "Click me"
		}
		val label1 = new Label {
			text = "This is the other window."
		}
		val label2 = new Label {
			text = "App with not end if I am closed."
		}
		contents = new BoxPanel(Orientation.Vertical) {
			contents += button
					contents += label1
					contents += label2
					border = Swing.EmptyBorder(30, 30, 10, 30)
		}

		// listeners
		listenTo(button)
		reactions += {
		case ButtonClicked(b) =>
		println("[UI  on " + Thread.currentThread + "]")
		log ! "Button clicked in other window"
		}
	}

	// define a logging Actor which will run in a
	// separate thread (pool) to the UI
	val log = new Actor {
		def act = {
				loop {
					react {
					case (message: String) =>
					println("[log on " + Thread.currentThread + "] " + message)
					}
				}
		}
	}

}