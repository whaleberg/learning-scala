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
			val c = pow(offset.x, 2) +pow(offset.y,2)
			val b = 2 * offset.x * targetVel.x + 2*offset.y * targetVel.y
			val a = pow(targetVel.x,2) + pow(targetVel.y,2) - pow(speed,2)					
					
			for {
			  (r1, r2) <- solveQuadraticForRealRoots(a, b, c)
			} yield { 
			  val t = (r1, r2) match {
			    case (r1, r2) if r1 >=0 && r2>=0 => min(r1, r2)
			    case (r1, _) if r1 >= 0 => r1
			    case (_ , r2) if r2 >= 0 => r2
			  }
			  
			  val collisionPos = targetPos+targetVel.scale(t);
			  new Point(collisionPos.x, collisionPos.y)
			}
			
	}


	/**
	 * solve equation of the form ax+b = 0 for x
	 */
	def solveLinear(a: Double, b:Double):Double = {
			val x = -b / a
			return x
	}
	
	/**
	 * solve equation for the form ax^2 + bx + c = 0
	 * for the real roots of x
	 * 
	 * if a = 0 it will solve it as a linear equation, but still return 2 roots
	 */
	def solveQuadraticForRealRoots(a: Double, b: Double, c: Double):Option[(Double, Double)]={
			a match{
			  case a if a == 0 =>
			    val r = solveLinear(b, c)
			    System.out.println(r);
			    Some(r, r)
			  case _ => 
				val disc = b*b - 4*a*c
				disc match {
				  case d if d < 0 => None
				  case d if d >= 0 =>
				  val r1 = (-b + sqrt(d))/(2 *a)
				  val r2 = (-b - sqrt(d))/(2* a)
				  System.out.println(r1 +","+ r2);
				  Some(r1, r2)
				  
				}
			}
			
	}

}