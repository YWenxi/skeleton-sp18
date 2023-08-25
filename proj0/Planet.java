public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	// Constant
	private static final double G = 6.67e-11;

	// Constructor
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = (p.xxPos - xxPos);
		double dy = (p.yyPos - yyPos);
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p) {
		double r = calcDistance(p);
		return G * mass * p.mass / r / r;
	}

	public double calcForceExertedByX(Planet p) {
		double dx = - xxPos + p.xxPos;
		double F = calcForceExertedBy(p);
		return F * dx / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double dy = - yyPos + p.yyPos;
		double F = calcForceExertedBy(p);
		return F * dy / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets)
	{
		double totalForce = 0;
		for (Planet planet : allPlanets) {
			if (this.equals(planet)) {
				continue;
			}
			totalForce += calcForceExertedByX(planet);
		}
		return totalForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets)
	{
		double totalForce = 0;
		for (Planet planet : allPlanets) {
			if (this.equals(planet)) {
				continue;
			}
			totalForce += calcForceExertedByY(planet);
		}
		return totalForce;
	}

	public void update(double dt, double Fx, double Fy) {
		double ax = Fx / mass;
		double ay = Fy / mass;

		xxVel = xxVel + dt * ax;
		yyVel = yyVel + dt * ay;

		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}
