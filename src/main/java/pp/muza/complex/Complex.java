package pp.muza.complex;

import pp.muza.complex.impl.ComplexUtils;

import java.beans.Transient;


/**
 * Complex number interface
 * <p>
 * It is used to represent a vector in a 2D or 3D space or a complex number.
 * Most methods change the state of the object.
 * Use static methods for mathematical operations that return a new object.
 */
@SuppressWarnings("unused")
public interface Complex {

    /**
     * The left vector
     */
    Complex LEFT = Complex.immutableOf(-1.0, 0.0);
    /**
     * The right vector
     */
    Complex RIGHT = Complex.immutableOf(1.0, 0.0);
    /**
     * The up vector
     */
    Complex UP = Complex.immutableOf(0.0, 1.0);
    /**
     * The down vector
     */
    Complex DOWN = Complex.immutableOf(0.0, -1.0);
    /**
     * The zero vector
     */
    Complex ZERO = Complex.immutableOf(0.0, 0.0);

    /**
     * The X dimension index
     */
    int X = 0;
    /**
     * The Y dimension index
     */
    int Y = 1;
    /**
     * The Z dimension index
     */
    int Z = 2;
    /**
     * The W dimension index
     */
    int W = 3;
    /**
     * The index of the radius for polar coordinates
     */
    int R = 0;
    /**
     * The index of the angle for polar coordinates
     */
    int PHI = 1;
    /**
     * The default dimension size
     */
    double DEFAULT_DIMENSION = 2;

    /**
     * Angle 0 degrees in radians
     */
    double ANGLE_0 = 0;
    /**
     * Direction UP angle in radians
     */
    double ANGLE_UP = ANGLE_0;
    /**
     * Angle 30 degrees in radians
     */
    double ANGLE_30 = Math.PI / 6;
    /**
     * Angle 45 degrees in radians
     */
    double ANGLE_45 = Math.PI / 4;
    /**
     * Angle 60 degrees in radians
     */
    double ANGLE_60 = Math.PI / 3;
    /**
     * Angle 90 degrees in radians
     */
    double ANGLE_90 = Math.PI / 2;
    /**
     * Direction LEFT angle in radians
     */
    double ANGLE_LEFT = ANGLE_90;
    /**
     * Angle 180 degrees in radians
     */
    double ANGLE_180 = Math.PI;
    /**
     * Direction DOWN angle in radians
     */
    double ANGLE_DOWN = ANGLE_180;
    /**
     * Angle 270 degrees in radians
     */
    double ANGLE_270 = Math.PI * 3 / 2;
    /**
     * Direction RIGHT angle in radians
     */
    double ANGLE_RIGHT = ANGLE_270;
    /**
     * Angle 360 degrees in radians
     */
    double ANGLE_360 = Math.PI * 2;

    /**
     * The left-up vector
     */
    Complex UP_LEFT = Complex.immutableOf(-Math.cos(ANGLE_45), Math.sin(ANGLE_45));
    /**
     * The right-up vector
     */
    Complex UP_RIGHT = Complex.immutableOf(Math.cos(ANGLE_45), Math.sin(ANGLE_45));
    /**
     * The left-down vector
     */
    Complex DOWN_LEFT = Complex.immutableOf(-Math.cos(ANGLE_45), -Math.sin(ANGLE_45));
    /**
     * The right-down vector
     */
    Complex DOWN_RIGHT = Complex.immutableOf(Math.cos(ANGLE_45), -Math.sin(ANGLE_45));

    /**
     * Create a new complex number in 2D dimension
     *
     * @param x the first value
     * @param y the second value
     * @return a new complex number
     */
    static Complex of(double x, double y) {
        return ComplexUtils.of(x, y);
    }

    /**
     * Create a new complex number in 2D dimension, the value is immutable
     *
     * @param x the first value
     * @param y the second value
     * @return a new complex number
     */
    static Complex immutableOf(double x, double y) {
        return ComplexUtils.immutableOf(x, y);
    }

    /**
     * Create a new complex number in 3D dimension, the value is immutable
     *
     * @param x the first value
     * @param y the second value
     * @param z the third value
     * @return a new complex number
     */
    static Complex immutableOf(double x, double y, double z) {
        return ComplexUtils.immutableOf(x, y, z);
    }

    /**
     * Create a new immutable complex number from another complex number
     *
     * @param value the complex number
     * @return a new complex number
     */
    static Complex immutableOf(Complex value) {
        return ComplexUtils.immutableOf(value.get());
    }

    /**
     * Create a new complex number in 3D dimension
     *
     * @param x the first value
     * @param y the second value
     * @param z the third value
     * @return a new complex number
     */
    static Complex of(double x, double y, double z) {
        return ComplexUtils.of(x, y, z);
    }

    /**
     * Create a new complex number from array
     *
     * @param values the values
     * @return a new complex number
     */
    static Complex of(double... values) {
        return ComplexUtils.of(values);
    }

    /**
     * Create a new complex number from another complex number
     *
     * @param value the complex number
     * @return a new complex number
     */
    static Complex of(Complex value) {
        return ComplexUtils.of(value);
    }

    /**
     * Create a new complex number from polar coordinates in 2D
     *
     * @param r   the radius
     * @param phi the angle
     * @return a new complex number
     */
    static Complex fromPolar(double r, double phi) {
        return ComplexUtils.of(r * Math.cos(phi), r * Math.sin(phi));
    }

    /**
     * Create a new complex number by adding two complex numbers
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return a new complex number
     */
    static Complex add(Complex A, Complex B) {
        if (B.getDimension() > A.getDimension()) {
            throw new IllegalArgumentException("Illegal dimension");
        }
        Complex res = A.copy();
        res.change(B);
        return res;
    }

    /**
     * Create a new complex number by subtracting two complex numbers
     *
     * @param A the subtracted complex number
     * @param B the subtracting complex number
     * @return a new complex number
     */
    static Complex sub(Complex A, Complex B) {
        if (B.getDimension() > A.getDimension()) {
            throw new IllegalArgumentException("Illegal dimension");
        }
        Complex res = A.copy();
        res.dec(B);
        return res;
    }

    /**
     * It is a cross-product of two complex numbers
     * Supported dimensions: 2, 3
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return a new complex number
     */
    static Complex multiple(Complex A, Complex B) {
        if (A.getDimension() != B.getDimension()) {
            throw new IllegalArgumentException("dimensions are not equal");
        }
        int dim = A.getDimension();
        Complex res;

        switch (dim) {
            case 1:
                res = ComplexUtils.of(A.getValue(0) * B.getValue(0));
                break;
            case 2:
                res = ComplexUtils.of(A.getValue(0) * B.getValue(1) - A.getValue(1) * B.getValue(0));
                break;
            case 3:
                res = ComplexUtils.of(A.getValue(1) * B.getValue(2) - A.getValue(2) * B.getValue(1), A.getValue(2) * B.getValue(0) - A.getValue(0) * B.getValue(2), A.getValue(0) * B.getValue(1) - A.getValue(1) * B.getValue(0));
                break;
            default:
                throw new IllegalArgumentException("not implemented");
        }
        return res;
    }

    /**
     * Create a new complex number by scaling a complex number
     *
     * @param A the scaled complex number
     * @param d the scale
     * @return a new complex number
     */
    static Complex scale(Complex A, double d) {
        Complex res = A.copy();
        res.scale(d);
        return res;
    }

    /**
     * Dot product of two complex numbers. Dot product is a scalar value.
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return dot product of two complex numbers
     */
    static double dot(Complex A, Complex B) {
        int dim = Math.min(A.getDimension(), B.getDimension());
        double res = 0.0;
        for (int i = 0; i < dim; i++) {
            res += A.getValue(i) * B.getValue(i);
        }
        return res;
    }

    /**
     * Rotate the complex number by the angle
     *
     * @param complex the complex number (only for 2D)
     * @param angle   the angle in radians
     * @return a new complex number
     * @throws IllegalArgumentException if the dimension is not 2
     */
    static Complex rotate(Complex complex, double angle) {
        Complex res = complex.copy();
        res.rotate(angle);
        return res;
    }

    /**
     * Distance between two complex numbers
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return distance between two complex numbers
     */
    static double distance(Complex A, Complex B) {
        return Math.sqrt(sub(A, B).squareModule());
    }

    /**
     * Calculate the square distance between two complex numbers
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return square distance
     */
    static double squareDistance(Complex A, Complex B) {
        return sub(A, B).squareModule();
    }

    /**
     * Angle between two complex numbers
     *
     * @param A the first complex number
     * @param B the second complex number
     * @return angle between two complex numbers
     */
    static double angle(Complex A, Complex B) {
        if (A.getDimension() != B.getDimension()) {
            throw new IllegalArgumentException("dimensions are not equal");
        }
        if (A.isNormalized() && B.isNormalized()) {
            return Math.acos(dot(A, B));
        } else {
            return Math.acos(dot(A, B) / Math.sqrt(A.squareModule() * B.squareModule()));
        }
    }

    /**
     * Normalize the complex number and return a new complex number
     *
     * @param complex the complex number
     * @return normalized complex number
     */
    static Complex normalize(Complex complex) {
        Complex result = complex.copy();
        result.normalize();
        return result;
    }

    /**
     * Set value to null
     */
    void setNull();

    /**
     * Set value to unassigned
     */
    void setUnassigned();

    /**
     * Normalize the value
     */
    void normalize();

    /**
     * Check if the value is null
     *
     * @return true if the value is null
     */
    @Transient
    boolean isNull();

    /**
     * Check if the value is zero
     *
     * @return true if the value is zero
     */
    @Transient
    boolean isZero();

    /**
     * Check if the value is infinity
     *
     * @return true if the value is infinity
     */
    @Transient
    boolean isInfinity();

    /**
     * Check if the value is NaN
     *
     * @return true if the value is NaN
     */
    @Transient
    boolean isNaN();

    /**
     * Check if the value is unassigned
     *
     * @return true if the value is unassigned
     */
    @Transient
    boolean isUnassigned();

    /**
     * Check if the value is normalized
     *
     * @return true if the value is normalized
     */
    @Transient
    boolean isNormalized();

    /**
     * Decrement the value
     *
     * @param complex decrement value
     */
    void dec(Complex complex);

    /**
     * Decrement the value
     *
     * @param values decrement values
     */
    void dec(double... values);

    /**
     * Add the value to the current value
     * V = V + complex
     *
     * @param complex change value
     */
    void change(Complex complex);

    /**
     * Add the value
     * V = V + values
     *
     * @param values change values
     */
    void change(double... values);

    /**
     * Scale the value
     * V = V * scale
     *
     * @param scale scale factor
     */
    void scale(double scale);

    /**
     * Rotate the vector by the angle, only for two dimensions complex
     *
     * @param angle the angle in radians
     */
    void rotate(double angle);

    /**
     * Return the square module of the value
     * S = X*X + Y*Y + Z*Z ...
     *
     * @return square module
     */
    double squareModule();

    /**
     * Return value of the dimension index
     *
     * @param index dimension index
     * @return value
     */
    @Transient
    double getValue(int index);

    /**
     * Set value of the dimension index
     *
     * @param index dimension index
     * @param value value
     */
    void setValue(int index, double value);

    /**
     * Return values as array
     *
     * @return array of values
     */
    double[] get();

    /**
     * Set values from array
     *
     * @param values array of values
     */
    void set(double... values);

    /**
     * Set values from another complex
     *
     * @param source source complex
     */
    void set(Complex source);

    /**
     * Return the dimension of the value
     *
     * @return dimension
     */
    @Transient
    int getDimension();

    /**
     * Create a copy of the value
     *
     * @return copy of the value
     */
    Complex copy();

    /**
     * Compare the value with another complex with epsilon
     *
     * @param complex complex to compare
     * @param epsilon the maximum difference between the values
     * @return copy of the value
     */
    boolean equals(Complex complex, double epsilon);

    /**
     * The method returns the current version. When a setter is called on, the version increases by one.
     *
     * @return version
     */
    @Transient
    int getVersion();

}
