package com.zhihu.android.opengltest.objects;

import android.opengl.GLES20;

import com.zhihu.android.opengltest.Constants;
import com.zhihu.android.opengltest.data.VertexArray;
import com.zhihu.android.opengltest.programs.ColorShaderProgram;

public class Mallet {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;
    private static final float[] VERTEX_DATA = {
            0.0f, -0.4f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.4f, 1.0f, 0.0f, 0.0f
    };

    private VertexArray vertexArray;

    public Mallet() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorShaderProgram) {
        vertexArray.setVertexAttribPointer(0, colorShaderProgram.getaPositionLocation(), POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, colorShaderProgram.getaColorLocation(), COLOR_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 2);
    }
}
