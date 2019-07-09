package com.zhihu.android.opengltest.objects;

import android.opengl.GLES20;

import com.zhihu.android.opengltest.Constants;
import com.zhihu.android.opengltest.data.VertexArray;
import com.zhihu.android.opengltest.programs.TextureShaderProgram;

public class Table {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int TEXTRUE_COORDINATE_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTRUE_COORDINATE_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;
    private static final float[] VERTEX_DATA = {
            0.0f,0.0f,0.5f,0.5f,
            -0.5f,-0.8f,0.0f,0.9f,
            0.5f,-0.8f,1.0f,0.9f,
            0.5f,0.8f,1.0f,0.1f,
            -0.5f,0.8f,0.0f,0.1f,
            -0.5f,-0.8f,0.0f,0.9f
    };

    private VertexArray vertexArray;

    public Table(){
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram textureShaderProgram){
        vertexArray.setVertexAttribPointer(0,textureShaderProgram.getaPositionLocation(),POSITION_COMPONENT_COUNT,STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT,textureShaderProgram.getaTextureCoordinatesLocation(),TEXTRUE_COORDINATE_COMPONENT_COUNT,STRIDE);
    }

    public void draw(){
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,0,6);
    }
}
