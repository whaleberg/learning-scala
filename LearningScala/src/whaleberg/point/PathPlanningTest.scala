package whaleberg.point

object PathPlanningTest extends App {
	val p1 = new Point(10, 10)
	val v1 = new Point(-1, 0)
	val p2 = new Point(0, 0)
	val s2 = 2
	
	System.out.println(PathPlanning.closestCollisionWithConstantVelocity(p1, v1, p2, s2))
}