package com.ba.field.field;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

import com.ba.domain.Noticia;
import com.ba.util.BAStringUtils;
import com.pegsoluciones.blackberry.common.util.GraphicUtil;
import com.pegsoluciones.blackberry.common.util.PosicionesUtil;
import com.pegsoluciones.blackberry.common.web.DescargaImagenesManager;
import com.pegsoluciones.blackberry.common.web.EsperaImagenInterface;

public class NoticiaField extends Field implements EsperaImagenInterface {

	private static final Font FONT_TITULO = Font.getDefault().derive(Font.BOLD,
			PosicionesUtil.UbicarEn(16, 18, 20, 0));
	private static final Font FONT_RESUMEN = Font.getDefault().derive(Font.BOLD,
			PosicionesUtil.UbicarEn(14, 16, 18, 0));
	
	
	private static final int MARGEN_X = 10;
	private static final int MARGEN_Y = 10;
	private static final int ESPACIO_TITU_RESU = 20;
	private static final int ANCHO_IMAGEN = 50;

	
	private Noticia noticia;
	private int colorFuente = Color.DARKGRAY;

	private int alto;
	private Bitmap imagen; 

	public NoticiaField(Noticia noticia) {
		super(FOCUSABLE);
		this.noticia = noticia;
		
		Graphics g = new Graphics(new Bitmap(0, 0));
		int r1 = BAStringUtils.drawTituloPixel(g, MARGEN_X,
				MARGEN_Y, noticia.getTitulo(),Display.getWidth() - 3 * MARGEN_X - ANCHO_IMAGEN, 2);
		
		int r2 = BAStringUtils.drawTituloPixel(g, MARGEN_X,
				MARGEN_Y, noticia.getDetalle(),Display.getWidth() - 3 * MARGEN_X - ANCHO_IMAGEN, 2);
		
			
		alto = MARGEN_Y * 2 + r1 * FONT_TITULO.getHeight() + ESPACIO_TITU_RESU 
			+ r2 * FONT_RESUMEN.getHeight();
		
		//DescargaImagenesManager.descargarImagenThread(noticia.getImagen(), this);
		
	}

	protected void layout(int width, int height) {
		setExtent(Display.getWidth(),alto);
	}

	protected void paint(Graphics g) {
		
		g.setBackgroundColor(Color.WHITE);
		g.clear();
		
		int x = MARGEN_X;
		
		if(imagen != null){
			GraphicUtil.drawBitmap(g, imagen, x, MARGEN_Y);
			x += MARGEN_X + imagen.getWidth();
		}
				
		g.setColor(colorFuente);
		
		g.setFont(FONT_TITULO);
		
		
		
		int r = BAStringUtils.drawTituloPixel(g, x, MARGEN_Y, noticia.getTitulo(),
				Display.getWidth() - x - MARGEN_X, 2);
		
		int y = r * FONT_TITULO.getHeight() + ESPACIO_TITU_RESU;
		g.setFont(FONT_RESUMEN);
		
		int r2 = BAStringUtils.drawTituloPixel(g, x, y, noticia.getDetalle(),
				Display.getWidth() - x -  MARGEN_X, 2);
		
		
	}

	protected void onFocus(int direction) {		
		colorFuente = Color.BLACK;
		super.onFocus(direction);
	}

	protected void onUnfocus() {
		colorFuente = Color.DARKGRAY;
		super.onUnfocus();
		invalidate();
	}

	public boolean trackwheelClick(int time, int status){
//		SimulaScreens.pushManager(new DetalleNoticiaManager(noticia));
		return true;
	}

	public void catchImagen(Bitmap b, String ruta) {
		if(b != null){
			imagen = b;
			invalidate();
		}
	}
	
}
