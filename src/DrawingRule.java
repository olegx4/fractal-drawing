public class DrawingRule {
    private int rangeForPoint;
    private int numberOfFigureTopPoints;

    public DrawingRule(int rangeForPoint, int numberOfFigureTopPoints) {
        this.rangeForPoint = rangeForPoint;
        this.numberOfFigureTopPoints = numberOfFigureTopPoints;
    }

    public int findIndexOfTop(int randomNumber) {
        int indexOfTop = 0;
        for (int i = 0; i < numberOfFigureTopPoints;){
            if ((randomNumber >= i) && (randomNumber <= i + rangeForPoint)) {
                indexOfTop = i;
            } else {
                i++;
            }
        }
        return indexOfTop;
    }
}
