public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        Planet[] planets = new Planet[numPlanet];
        in.readDouble();
        int i = 0;
        while (i < numPlanet) {
            planets[i] = new Planet(
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readDouble(),
                    in.readString()
            );
            i = i + 1;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        int numPlanet = planets.length;

        /* Initialize window */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();


        /* Load background. */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Draw each planet. */
        for (Planet planet: planets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double t = 0; t < T; t = t + dt) {
            double[] xForces = new double[numPlanet];
            double[] yForces = new double[numPlanet];

            for (int i = 0; i < numPlanet; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Update. */
            for (int i = 0; i < numPlanet; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw */
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet: planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
