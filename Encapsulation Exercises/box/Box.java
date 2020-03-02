package box;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public double getLength() {
        return length;
    }

    private void setLength(double length) {
        validate(length);
        this.length = length;
    }

    private void validate(double number){
        if (number<= 0){
            throw new IllegalArgumentException("Input cannot be zero or negative");
        }
    }

    public double getWidth() {
        return width;
    }
    //Volume = lwh
    //Lateral Surface Area = 2lh + 2wh
    //Surface Area = 2lw + 2lh + 2wh

    public double calculateVolume(){
        return this.getWidth() * this.getHeight();
    }

    public double calculateLateralSurfaceArea(){
        return (2*this.getLength() * this.getHeight())
                + (2*this.getWidth()*this.getHeight());
    }

    public double calculateSurfaceArea(){
        return  (2*this.getLength()*this.getWidth())
                + (2*this.getLength() * this.getHeight())
                + (2*this.getWidth() * getHeight());
    }

    private void setWidth(double width) {
        validate(width);
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        validate(height);
        this.height = height;
    }
}
