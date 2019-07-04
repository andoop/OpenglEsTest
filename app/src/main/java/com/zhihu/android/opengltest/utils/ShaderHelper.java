package com.zhihu.android.opengltest.utils;

import android.util.Log;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        int shaderObjectId = glCreateShader(type);
        if (shaderObjectId == 0) {
            if (LogConfig.ON)
                Log.i(TAG, "compileShader: 创建 shader 失败：" + shaderCode);
            return 0;
        }
        glShaderSource(type, shaderCode);
        int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        if (LogConfig.ON)
            Log.i(TAG, "compileShader: 编译结果：" + compileStatus[0]+" "+shaderCode);
        if (compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId);
        }
        if (LogConfig.ON)
            Log.i(TAG, "compileShader: 编译信息：" + glGetShaderInfoLog(shaderObjectId));

        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        int programObjectId = glCreateProgram();
        if (programObjectId == 0) {
            if (LogConfig.ON)
                Log.i(TAG, "linkProgram: 创建 program 失败");
            return 0;
        }
        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);
        glLinkProgram(programObjectId);
        int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            glDeleteProgram(programObjectId);
            if (LogConfig.ON)
                Log.i(TAG, "linkProgram: link 失败");
        }
        if (LogConfig.ON)
            Log.i(TAG, "linkProgram: link 信息 " + glGetProgramInfoLog(programObjectId));
        return programObjectId;
    }

    public static boolean validateProgram(int program) {
        glValidateProgram(program);
        int[] validateStatus = new int[1];
        glGetProgramiv(program, GL_VALIDATE_STATUS, validateStatus, 0);
        Log.i(TAG, "validateProgram: " + glGetProgramInfoLog(program));
        return validateStatus[0] != 0;
    }

}
