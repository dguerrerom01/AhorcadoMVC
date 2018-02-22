package controlador;

import java.awt.EventQueue;

import vista.AhorcadoUI;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.xml.stream.util.StreamReaderDelegate;

import java.awt.event.ActionEvent;

public class Principal extends AhorcadoUI {

	Control control = new Control();

	public Principal() {
		super();

		txtPalabraSecreta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entregaPalabra();
				}
			}
		});

		btnYa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entregaPalabra();
			}
		});

		txtLetra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtLetra.getText().isEmpty()) {
						txtMensaje.setText("Introduzca una letra");
					} else {
						char letra = txtLetra.getText().charAt(0);
						txtLetra.setText(null);
						txtMensaje.setText(null);
						if (control.getFallos() < 6) {
							if (control.anotarAciertos(letra)) {
								txtAciertos.setText(control.getAciertos());
								if (control.palabraAcertada()) {
									txtLetra.setEditable(false);
									txtLetra.setFocusable(false);
									txtMensaje.setText("Has ganado");
								}
							} else {
								txtFallos.setText(String.valueOf(control.getFallos()));

							}
						} else {
							txtMensaje.setText("Has perdido");
							txtLetra.setEditable(false);
							txtLetra.setFocusable(false);
						}
					}
				}
			}
		});

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// IMPORTANTE: Se crea un objeto de Principal no del UI
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void entregaPalabra() {
		if (txtPalabraSecreta.getText().isEmpty())
			txtMensaje.setText("Escribe algo");
		else {
			// aqui hemos llegado porque la palabra es valida
			control.setPalabreja(txtPalabraSecreta.getText());
			control.iniciaAciertos();
			// comportamiento del ui tras validar palabra
			// Ocultar los componentes que se han usado para introducir la
			// palabra secreta
			txtPalabraSecreta.setVisible(false);
			lblPalabraSecreta.setVisible(false);
			btnYa.setVisible(false);
			// hacemos visible el titulo
			lblTitulo.setVisible(true);
			// habilitar el txt de la letra
			txtLetra.setEditable(true);
			txtMensaje.setText("");
		}
	}
}


