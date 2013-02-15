package whaleberg.point

import scala.math._

object PathPlanning {

	/**
	 * based on the algorithm here
	 * http://stackoverflow.com/questions/10358022/find-th

	 * sx^2 + sy^2 + (2 * sx * vx + 2 * sy * vy) * t + (vx^2 + vy^2 - v2^2) * t^2 = 0
	 * \---   ---/   \------------   ----------/       \--------   ------/
	 *     \ /                    \ /                           \ /
	 *      c                      b                             a
	 */
	def closestCollisionWithConstantVelocity(targetPos: Point, targetVel: Point,
			currentPos: Point, speed : Double): Option[Point] = {

			val offset = targetPos - currentPos
					val c = offset.x*offset.x + offset.y*offset.y
					val b = 2 * offset.x * targetVel.x + 2*offset.y * targetVel.y
					val a = targetVel.x*targetVel.x + targetVel.y*targetVel.y -speed*speed
					for{ (r1, r2) <- solveQuadraticForRealRoots(a, b, c)}
			yield{ val t = min (r1, r2);
			val collisionPos = targetPos+targetVel.scale(t);
			new Point(collisionPos.x, collisionPos.y)}

	}




	def solveQuadraticForRealRoots(a: Double, b: Double, c: Double):Option[(Double, Double)]={
			val disc = b*b - 4*a*c
					disc match {
					case d if d < 0 => None
					case d if d >= 0 =>
					val r1 = (-b + d)/(2 *a)
					val r2 = (-b - d)/(2* a)
					Some(r1, r2)
			}
	}

}