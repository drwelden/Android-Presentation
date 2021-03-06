package com.tiltdev.multitouchtest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.tiltdev.multitouchtest.view.TouchSurfaceView;

import android.app.Activity;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;

public class MultiTouchTest extends Activity {
    private TouchSurfaceView mGLSurfaceView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mGLSurfaceView = new TouchSurfaceView(this);
        mGLSurfaceView.setRenderer(new Renderer() {
        	
			public void onDrawFrame(GL10 gl) {
				// TODO Auto-generated method stub
				gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
				float[] x = mGLSurfaceView.getXPoints();
				float[] y = mGLSurfaceView.getYPoints();
				
				float[] vertices = new float[x.length*2];
				short[] indices = new short[x.length];
				
				for (int i = 0; i < x.length; i++) {
					vertices[0+(2*i)] = x[i];
					vertices[1+(2*i)] = -y[i];
					indices[i] = (short)i;
				}
				
				FloatBuffer vertexBuffer;
				ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
				vbb.order(ByteOrder.nativeOrder());
				vertexBuffer = vbb.asFloatBuffer();
				vertexBuffer.put(vertices);
				vertexBuffer.position(0);
				
				ShortBuffer indexBuffer;
				ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length*2);
				ibb.order(ByteOrder.nativeOrder());
				indexBuffer = ibb.asShortBuffer();
				indexBuffer.put(indices);
				indexBuffer.position(0);
				
				gl.glLoadIdentity();
				gl.glTranslatef(-1.0f, 1.0f, 0);
				gl.glPointSize(4.0f);
				gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
				gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
				gl.glDrawElements(GL10.GL_LINES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
				gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
				
			}

			public void onSurfaceChanged(GL10 gl, int width, int height) {
				//This is where we'd accommodate the resolution change if we aren't locking down the orientation (which we will)
			}

			public void onSurfaceCreated(GL10 gl, EGLConfig config) {
				// Set the background color to black ( rgba ).
				gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);				
			}
        	
        });
        
        setContentView(mGLSurfaceView);
    }
}