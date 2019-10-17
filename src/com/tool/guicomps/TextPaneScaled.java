package com.tool.guicomps;

/**
 * @author Stanislav Lapitsky
 * @version 1.0
 *  by http://java-sl.com/articles.html
 */

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.Font;

public class TextPaneScaled extends JEditorPane
{

    JComboBox zoomCombo = new JComboBox(new String[] {"50%", "75%",
                                        "100%", "150%", "200%"});
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextPaneScaled scaledTextPane = new TextPaneScaled();
        scaledTextPane.getDocument().putProperty("i18n", Boolean.FALSE);
        scaledTextPane.getDocument().putProperty("ZOOM_FACTOR",  Double.valueOf(1.5));
        JScrollPane scroll = new JScrollPane(scaledTextPane);
        frame.getContentPane().add(scroll);
        frame.getContentPane().add(scaledTextPane.zoomCombo, BorderLayout.NORTH);

        frame.setSize(600, 200);
        frame.setVisible(true);
    }

    public TextPaneScaled() {
        super();
        final SimpleAttributeSet attrs=new SimpleAttributeSet();
        StyleConstants.setFontSize(attrs,16);
        setEditorKit(new ScaledEditorKit());
        StyledDocument doc=(StyledDocument)TextPaneScaled.this.getDocument();
        doc.setCharacterAttributes(0,1,attrs,true);
        try {
            StyleConstants.setFontFamily(attrs,"Lucida Sans");
            doc.insertString(0, "Lusida Sans font test\n", attrs);

            StyleConstants.setFontFamily(attrs,"Lucida Bright");
            doc.insertString(0, "Lucida Bright font test\n", attrs);

            StyleConstants.setFontFamily(attrs,"Lucida Sans Typewriter");
            doc.insertString(0, "Lucida Sans Typewriter font test\n", attrs);
        }
        catch (BadLocationException ex) {
        }

        zoomCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) zoomCombo.getSelectedItem();
                s = s.substring(0, s.length() - 1);
                double scale = Double.valueOf(s).doubleValue() / 100;
                TextPaneScaled.this.getDocument().putProperty("ZOOM_FACTOR",Double.valueOf(scale));

                try {
                    StyledDocument doc=(StyledDocument)TextPaneScaled.this.getDocument();
                    doc.setCharacterAttributes(0,1,attrs,true);
                    doc.insertString(0, "", null); //refresh
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        zoomCombo.setSelectedItem("100%");
    }

    public void repaint(int x, int y, int width, int height) {
        super.repaint(0, 0, getWidth(), getHeight());
    }

}

class ScaledEditorKit extends StyledEditorKit {
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }

    class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                }
                else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                }
                else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new ScaledView(elem, View.Y_AXIS);
                }
                else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                }
                else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }

    }
}

//-----------------------------------------------------------------
class ScaledView extends BoxView {
    public ScaledView(Element elem, int axis) {
        super(elem, axis);
    }

    public double getZoomFactor() {
        Double scale = (Double) getDocument().getProperty("ZOOM_FACTOR");
        if (scale != null) {
            return scale.doubleValue();
        }

        return 1;
    }

    public void paint(Graphics g, Shape allocation) {
        Graphics2D g2d = (Graphics2D) g;
        double zoomFactor = getZoomFactor();
        AffineTransform old = g2d.getTransform();
        g2d.scale(zoomFactor, zoomFactor);
        super.paint(g2d, allocation);
        g2d.setTransform(old);
    }

    public float getMinimumSpan(int axis) {
        float f = super.getMinimumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getMaximumSpan(int axis) {
        float f = super.getMaximumSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    public float getPreferredSpan(int axis) {
        float f = super.getPreferredSpan(axis);
        f *= getZoomFactor();
        return f;
    }

    protected void layout(int width, int height) {
        super.layout(Double.valueOf(width / getZoomFactor()).intValue(),
                     Double.valueOf(height *
                                getZoomFactor()).intValue());
    }

    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        double zoomFactor = getZoomFactor();
        Rectangle alloc;
        alloc = a.getBounds();
        Shape s = super.modelToView(pos, alloc, b);
        alloc = s.getBounds();
        alloc.x *= zoomFactor;
        alloc.y *= zoomFactor;
        alloc.width *= zoomFactor;
        alloc.height *= zoomFactor;

        return alloc;
    }

    public int viewToModel(float x, float y, Shape a,
                           Position.Bias[] bias) {
        double zoomFactor = getZoomFactor();
        Rectangle alloc = a.getBounds();
        x /= zoomFactor;
        y /= zoomFactor;
        alloc.x /= zoomFactor;
        alloc.y /= zoomFactor;
        alloc.width /= zoomFactor;
        alloc.height /= zoomFactor;

        return super.viewToModel(x, y, alloc, bias);
    }

}
/*class MyLabelView extends LabelView {
    protected GlyphVector glyphs=null;
    public MyLabelView(Element elem) {
        super(elem);
    }
    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        Shape res = super.modelToView(pos,a,b);
        Rectangle rect=res instanceof Rectangle?(Rectangle)res:res.getBounds();
        if (glyphs!=null) {
            int i = pos - getStartOffset();
            Point2D point = glyphs.getGlyphPosition(i);
            rect.x = (int)(a.getBounds().getX()+point.getX())-1;
        }
        return rect;
    }
    protected void initGlyphVector(Graphics g) {
        try {
            Graphics2D g2d = (Graphics2D) g;
            FontRenderContext frc = g2d.getFontRenderContext();
            String str = "";
            str = getElement().getDocument().getText(getStartOffset(), getEndOffset() - getStartOffset());
            Font font = ( (StyledDocument) getElement().getDocument()).getFont(this.getAttributes());
            String fontFile = "C:/WINNT/Fonts/tahoma.ttf";
            BaseFont kernedFont = BaseFont.createFont(fontFile, BaseFont.WINANSI, false);
            double charSpacing = 0;
            glyphs = font.createGlyphVector(frc, str);
            double fontSize = font.getSize2D();
            double x = 0;
            int num = glyphs.getNumGlyphs();
            for (int i = 0; i < num; i++) {
                Point2D pos = glyphs.getGlyphPosition(i);
                GlyphMetrics gm = glyphs.getGlyphMetrics(i);
//                pos.x = x;
                pos.setLocation(x, pos.getY());
                glyphs.setGlyphPosition(i, pos);
                x += gm.getAdvance();
                if (i < num - 1) {
                    double kern = kernedFont.getKerning(str.charAt(i), str.charAt(i + 1));
                    x += charSpacing * 0.001f * fontSize + kern * 0.001f * fontSize;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void paint(Graphics g, Shape a) {
//        if (glyphs == null) {
            initGlyphVector(g);
//        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.drawGlyphVector(glyphs, 0, a.getBounds().height);
        g2d.setColor(Color.red);
        g2d.draw(a);
    }
*/
