package myMath;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import myMath.Polynom;

/** This class use JFrame and GRAL libraries to plot and display polynoms.
 * 
 * @author FerasTr */
public class PolynomGraph extends JFrame
{
	private static final long serialVersionUID = 1L; // For serializing.
	private Polynom p;
	private double start;
	private double end;
	private double eps; // Default steps = 0.25
	ArrayList<Double> extreme;

	/** Default plot with eps = 0.25.
	 * 
	 * @param fun function we want to draw
	 * @param x0  Start point.
	 * @param x1  End point. */
	public PolynomGraph(Polynom fun, double x0, double x1)
	{
		this(fun, x0, x1, 0.25);
	}

	/** Constructor with custom tolerance of error.
	 * 
	 * @param fun function we want to draw
	 * @param x0  Start point.
	 * @param x1  End point.
	 * @param eps Tolerance of error. */
	public PolynomGraph(Polynom fun, double x0, double x1, double eps)
	{
		this.p = new Polynom(fun);
		this.start = x0;
		this.end = x1;
		this.eps = eps;
		extreme = this.p.extremeMinMax(x0, x1, eps);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 1000);

		@SuppressWarnings("unchecked")
		DataTable data = new DataTable(Double.class, Double.class); // All points.
		@SuppressWarnings("unchecked")
		DataTable dataMaxMin = new DataTable(Double.class, Double.class); // Extreme points only.

		// Adding points.
		for (double x = this.start; x <= this.end; x += this.eps)
		{
			// Calculate y of x.
			double y = this.p.f(x);
			if (extreme.contains(x))
			{
				dataMaxMin.add(x, y);
			}
			data.add(x, y);

		}

		// Start plotting.
		XYPlot draw = new XYPlot(data, dataMaxMin);

		// Add points to frame.
		getContentPane().add(new InteractivePanel(draw));

		// Add lines.
		LineRenderer lines = new DefaultLineRenderer2D();
		draw.setLineRenderers(data, lines);

		// Color lines and extreme points.
		draw.getPointRenderers(dataMaxMin).get(0).setColor(new Color(1.0f, 0.0f, 0.0f));
		draw.getPointRenderers(data).get(0).setColor(new Color(0.0f, 0.0f, 0.0f));
		draw.getLineRenderers(data).get(0).setColor(new Color(0.0f, 0.0f, 0.0f));
	}

	public static void main(String[] args)
	{
		Polynom p = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5");
		// p.display(-2, 6, 0.25);

		p = new Polynom("0.2*x^2 - 2*x^1 + 2");
		//p.display(-4, 14, 0.25);
		
		p = new Polynom("1*x^1");
		p.display(-30, 30, 0.25);
	}
}