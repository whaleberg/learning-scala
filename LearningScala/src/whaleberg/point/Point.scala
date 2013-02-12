package whaleberg.point

/** using methods described here
 * http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
 */

class Point(val x: Double, val y: Double) {

	def +(p : Point): Point = new Point(x+p.x, y+p.y)
	def -(p : Point): Point = new Point(x-p.x, y-p.y)
	def cross(p : Point): Double = x*p.y - y*p.x
	def scale(scalar: Double): Point = new Point(x*scalar, y*scalar)
	override def toString() : String = "("+x+","+y+")"
}

abstract class Intersection()
case class Parrallel extends Intersection
case class Collinear extends Intersection
case class Approach(closest: Point) extends Intersection
case class Intersect(intersect: Point) extends Intersection


class Segment( val start: Point, val end: Point){
	val r = end - start;

	def intersects(seg :Segment): Intersection={
			val p = start
					val s = seg.r
					val q = seg.start

					val parrallel = (r cross s) == 0
					val collinear = ((q - p) cross r) == 0

					if ( collinear) {
						return Collinear()
					} else if (parrallel){
						return Parrallel()
					}
					else {
						val t = ((q - p) cross s )/ (r cross s);
						val u = ((q - p) cross r )/ (r cross s);
						val closest = p +(r scale t);

						if( t >= 0 && t <= 1 && u >= 0 && u <= 1){
							return Intersect(closest);
						} else {
							return Approach(closest)
						}
					}
	}

}


