package com.zhihu.android.opengltest.programs;

import android.content.Context;
import android.opengl.GLES20;

import com.zhihu.android.opengltest.utils.ShaderHelper;
import com.zhihu.android.opengltest.utils.TextResourceReader;

public class ShaderProgram {
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    protected final int progame;

    protected ShaderProgram(Context context, int vertexShaderResource, int fragmentShaderResource) {
        progame = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResource),
                TextResourceReader.readTextFileFromResource(context, fragmentShaderResource));
    }

    public void useProgram() {
        GLES20.glUseProgram(progame);
    }
}
