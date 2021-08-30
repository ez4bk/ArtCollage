/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage
 *
 *  @author: Yuchen Wei
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;

    // A picture of tileDimension x tileDimension
    private Picture unitPicture;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {

    // WRITE YOUR CODE HERE
    tileDimension = 100;
    collageDimension = 4;
    int w = tileDimension * collageDimension;
    int h = tileDimension * collageDimension;
    original = new Picture(filename);
    collage = new Picture(w,h);

    for (int tcol = 0; tcol < w; tcol++){
        for (int trow = 0; trow < h; trow++){
            int scol = tcol * original.width()  / w;
            int srow = trow * original.height() / h;
            Color color = original.get(scol, srow);
            collage.set(tcol, trow, color);
        }
    }
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {

        // WRITE YOUR CODE HERE
        tileDimension = td;
        collageDimension = cd;
        int w = tileDimension * collageDimension;
        int h = tileDimension * collageDimension;
        original = new Picture(filename);
        collage = new Picture(w,h);

        for (int tcol = 0; tcol < w; tcol++){
            for (int trow = 0; trow < h; trow++){
                int scol = tcol * original.width()  / w;
                int srow = trow * original.height() / h;
                Color color = original.get(scol, srow);
                collage.set(tcol, trow, color);
            }
        }
        }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

        // WRITE YOUR CODE HERE
        return collageDimension;
        }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

        // WRITE YOUR CODE HERE
        return tileDimension;
        }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

        // WRITE YOUR CODE HERE
        return original;
        }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

        // WRITE YOUR CODE HERE
        return collage;
        }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {

        // WRITE YOUR CODE HERE
        original.show();
        }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {

        // WRITE YOUR CODE HERE
        collage.show();
        }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture replaceOriginal = new Picture(filename);
        Picture replaceSized = new Picture(tileDimension, tileDimension);
        for (int tcol = 0; tcol < tileDimension; tcol++){
            for (int trow = 0; trow < tileDimension; trow++){
                int scol = tcol * replaceOriginal.width()  / tileDimension;
                int srow = trow * replaceOriginal.height() / tileDimension;
                Color color = replaceOriginal.get(scol, srow);
                replaceSized.set(tcol, trow, color);
            }
        }

        for (int i=0; i<tileDimension; i++){
            for (int j=0; j<tileDimension; j++){
                Color color2 = replaceSized.get(i,j);
                collage.set(i+tileDimension*collageCol, j+tileDimension*collageRow, color2);
            }
        }
        
        //collage.show();

        }

    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {

        // WRITE YOUR CODE HERE
        unitPicture = new Picture(tileDimension,tileDimension);
        for (int tcol = 0; tcol < tileDimension; tcol++){
            for (int trow = 0; trow < tileDimension; trow++){
                int scol = tcol * collage.width()  / tileDimension;
                int srow = trow * collage.height() / tileDimension;
                Color color = collage.get(scol, srow);
                unitPicture.set(tcol, trow, color);
            }
        }
        //unitPicture.show();

        for (int col = 0; col < tileDimension; col++) {
            for (int row = 0; row < tileDimension; row++) {
                Color color = unitPicture.get(col, row);
                for (int i = 0; i < collageDimension; i++) {
                    for (int j = 0; j < collageDimension; j++) {
                        collage.set(tileDimension*j + col, tileDimension*i + row, color);
                    }
                }
            }
        }
        //collage.show();
        }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture pictureR = new Picture(tileDimension, tileDimension);
        Picture pictureG = new Picture(tileDimension, tileDimension);
        Picture pictureB = new Picture(tileDimension, tileDimension);
        Picture colorPicture = new Picture(tileDimension, tileDimension);

        for (int col = 0; col < tileDimension; col++) {
            for (int row = 0; row < tileDimension; row++) {
                Color color = collage.get(tileDimension*collageCol+col,tileDimension*collageRow+row);
                colorPicture.set(col, row, color);
            }
        }

        // separate colors
        for (int col = 0; col < tileDimension; col++) {
            for (int row = 0; row < tileDimension; row++) {
                Color color = colorPicture.get(col, row);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                pictureR.set(col, row, new Color(r, 0, 0));
                pictureG.set(col, row, new Color(0, g, 0));
                pictureB.set(col, row, new Color(0, 0, b));
            }
        }

        component = component.toLowerCase();
        if (component == "red"){
            colorPicture = pictureR;
        }else if (component == "green"){
            colorPicture = pictureG;
        }else if (component == "blue"){
            colorPicture = pictureB;
        }else{
            throw new IllegalArgumentException("Unable to colorzie with the argument");
        }

        for (int i=0; i<tileDimension; i++){
            for (int j=0; j<tileDimension; j++){
                Color color = colorPicture.get(i,j);
                collage.set(i+tileDimension*collageCol, j+tileDimension*collageRow, color);
            }
        }

        }

    /*
     * Greyscale tile at (collageCol, collageRow)
     * (see Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void greyscaleTile (int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture greyPicture = new Picture(tileDimension, tileDimension);

        for (int col = 0; col < tileDimension; col++) {
            for (int row = 0; row < tileDimension; row++) {
                Color color = collage.get(tileDimension*collageCol+col,tileDimension*collageRow+row);
                greyPicture.set(col, row, color);
            }
        }

        for (int col = 0; col < tileDimension; col++) {
            for (int row = 0; row < tileDimension; row++) {
                Color color = greyPicture.get(col, row);
                Color grey = Luminance.toGray(color);
                greyPicture.set(col, row, grey);
            }
        }

        for (int i=0; i<tileDimension; i++){
            for (int j=0; j<tileDimension; j++){
                Color color2 = greyPicture.get(i,j);
                collage.set(i+tileDimension*collageCol, j+tileDimension*collageRow, color2);
            }
        }

        }

    // Test client 
    public static void main (String[] args) {
        ArtCollage art = new ArtCollage(args[0], 200, 4);
        art.makeCollage();
        //art.colorizeTile("red", 0, 0);
        art.replaceTile(args[1], 0, 0);
        art.greyscaleTile(0, 0);
        art.showCollagePicture();
        }
}
