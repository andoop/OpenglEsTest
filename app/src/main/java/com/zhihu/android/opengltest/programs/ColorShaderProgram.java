package com.zhihu.android.opengltest.programs;

import android.content.Context;
import android.opengl.GLES20;

import com.zhihu.android.opengltest.R;

public class ColorShaderProgram extends ShaderProgram {
    private int uMatrixLocation;
    private int aPositionLocation;
    private int aColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);
        uMatrixLocation = GLES20.glGetUniformLocation(progame, U_MATRIX);
        aPositionLocation = GLES20.glGetAttribLocation(progame, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(progame, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getaPositionLocation() {
        return aPositionLocation;
    }

    public int getaColorLocation() {
        return aColorLocation;
    }
}
