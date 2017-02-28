/*******************************************************************************
 *     DarkForge a Forge Hacked Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package uk.co.hexeption.darkforge.gui.gui.theme.themes.darkforge;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.hexeption.darkforge.gui.gui.base.Component;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentRenderer;
import uk.co.hexeption.darkforge.gui.gui.base.ComponentType;
import uk.co.hexeption.darkforge.gui.gui.elements.Frame;
import uk.co.hexeption.darkforge.gui.gui.theme.Theme;
import uk.co.hexeption.darkforge.utils.MathUtils;
import uk.co.hexeption.darkforge.utils.RenderUtils;
import uk.co.hexeption.darkforge.utils.render.GLUtils;

import java.awt.*;

/**
 * Created by Hexeption on 27/02/2017.
 */
@SideOnly(Side.CLIENT)
public class DarkForgeFrame extends ComponentRenderer {

    public DarkForgeFrame(Theme theme) {

        super(ComponentType.FRAME, theme);
    }

    @Override
    public void drawComponent(Component component, int mouseX, int mouseY) {

        Frame frame = (Frame) component;
        Dimension dimension = frame.getDimension();
        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isMaximized()) {
            isMaximized(frame, dimension, mouseX, mouseY);
        }

        RenderUtils.drawRect(frame.getX(), frame.getY(), frame.getX() + dimension.width, frame.getY() + 20, new Color(54, 54, 54, 160));
        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isMaximizible()) {
            isMaximizible(frame, dimension, mouseX, mouseY);
        }

        GLUtils.glColor(new Color(54, 54, 54));

        if (frame.isPinnable()) {
            isPinnable(frame, dimension, mouseX, mouseY);
        }
        GLUtils.glColor(new Color(54, 54, 54));
        theme.fontRenderer.drawCenteredString(frame.getText(), frame.getX() + 30, MathUtils.getMiddle(frame.getY(), (int) (frame.getY() + 18)) - (theme.fontRenderer.getHeight() / 10), Color.WHITE.hashCode());
        GLUtils.glColor(new Color(54, 54, 54));

    }


    private void isPinnable(Frame frame, Dimension dimension, int mouseX, int mouseY) {
        Color color = new Color(1, 1, 1);

        if (mouseX >= frame.getX() + dimension.width - 38 && mouseY >= frame.getY() && mouseY <= frame.getY() + 19 && mouseX <= frame.getX() + dimension.width - 19)
        {
            color = new Color(197, 239, 247, 50);
        }
        else
        {
            color = new Color(54, 54, 54, 160);
        }

        RenderUtils.drawRect(frame.getX() + dimension.width - 38, frame.getY(), frame.getX() + dimension.width - 19, frame.getY() + 20, color);
        RenderUtils.drawFilledCircle(MathUtils.getMiddle(frame.getX() + dimension.width - 38, frame.getX() + dimension.width - 19) + 1, MathUtils.getMiddle(frame.getY(), frame.getY() + 19) + 1, 3, Color.WHITE.getRGB());
        RenderUtils.drawHLine(frame.getX(), frame.getX() + dimension.width, frame.getY() , 0x8C808080);

        if (!frame.isPinned())
        {
            RenderUtils.drawFilledCircle(MathUtils.getMiddle(frame.getX() + dimension.width - 38, frame.getX() + dimension.width - 19) + 1, MathUtils.getMiddle(frame.getY(), frame.getY() + 19) + 1, 2, new Color(54, 54, 54).hashCode());
        }
    }

    private void isMaximizible(Frame frame, Dimension dimension, int mouseX, int mouseY) {
        Color color = new Color(1, 1, 1);

        if (mouseX >= frame.getX() + dimension.width - 19 && mouseY >= frame.getY() && mouseY <= frame.getY() + 19 && mouseX <= frame.getX() + dimension.width)
        {
            color = new Color(197, 239, 247, 50);
        }
        else
        {
            color = new Color(54, 54, 54, 160);
        }

        RenderUtils.drawRect(frame.getX() + dimension.width - 19, frame.getY(), frame.getX() + dimension.width, frame.getY() + 20, color);

        if (frame.isMaximized())
        {
            RenderUtils.drawTri(frame.getX() + dimension.width - 19 + 6, frame.getY() + 6, MathUtils.getMiddleDouble(frame.getX() + dimension.width - 19, frame.getX() + dimension.width), frame.getY() + 19 - 6, frame.getX() + dimension.width - 6, frame.getY() + 6, 1.5, Color.WHITE);
            RenderUtils.drawVLine(frame.getX() , frame.getY(), frame.getY() + dimension.height, 0x8C808080);
            RenderUtils.drawVLine(frame.getX() + dimension.width, frame.getY(), frame.getY() + dimension.height, 0x8C808080);
            RenderUtils.drawHLine(frame.getX(), frame.getX() + dimension.width, frame.getY() + dimension.height, 0x8C808080);
        }
        else
        {
            RenderUtils.drawTri(frame.getX() + dimension.width - 19 + 6, frame.getY() + 19 - 6, MathUtils.getMiddleDouble(frame.getX() + dimension.width - 19, frame.getX() + dimension.width), frame.getY() + 6, frame.getX() + dimension.width - 6, frame.getY() + 19 - 6, 1.8, Color.WHITE);
        }
    }

    private void isMaximized(Frame frame, Dimension dimension, int mouseX, int mouseY) {

        for (Component component : frame.getComponents()) {
            component.setxPos(frame.getX());
        }

        RenderUtils.drawRect(frame.getX(), frame.getY() + 1, frame.getX() + dimension.width, frame.getY() + dimension.height, new Color(31, 31, 31, 170));
        float height = 5;
        float maxHeight = 0;
        height = dimension.height - 19;

        for (Component component : frame.getComponents()) {
            maxHeight += component.getDimension().height;
        }

        float barHeight = height * (height / maxHeight);
        double y = (frame.getDimension().getHeight() - 19 - barHeight) * ((double) frame.getScrollAmmount() / (double) frame.getMaxScroll());
        y += frame.getY() + 19;
        frame.renderChildren(mouseX, mouseY);

        if (!(barHeight >= height)) {
            RenderUtils.drawRect((int) (frame.getX() + 1), (int) y, (int) (frame.getX()), (int) (y + barHeight), new Color(255, 255, 255));
        }

    }

    @Override
    public void doInteractions(Component component, int mouseX, int mouseY) {

        Frame frame = (Frame) component;
        int fontHeight = theme.fontRenderer.getHeight();
        Dimension area = frame.getDimension();

        if (mouseX >= frame.getX() + area.width - 19 && frame.isMaximizible() && mouseY >= frame.getY() && mouseY <= frame.getY() + 19 && mouseX <= frame.getX() + area.width)
        {
            frame.setMaximized(!frame.isMaximized());
        }

        if (mouseX >= frame.getX() + area.width - 38 && mouseY >= frame.getY() && mouseY <= frame.getY() + 19 && mouseX <= frame.getX() + area.width - 19)
        {
            frame.setPinned(!frame.isPinned());
        }
    }
}