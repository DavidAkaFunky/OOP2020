package woo.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exceptions.FileOpenFailedException;
import woo.exceptions.UnavailableFileException;
import woo.Storefront;

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<Storefront> {
  /** Input field. */
  private Input<String> _filenameToOpen;

  /**
   * Constructor.
   * 
   * @param receiver
   */
  public DoOpen(Storefront receiver) {
    super(Label.OPEN, receiver);
    _filenameToOpen = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.load(_filenameToOpen.value());
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    } catch(ClassNotFoundException | FileNotFoundException e) {
      throw new FileOpenFailedException(_filenameToOpen.value());
    } catch (IOException e) {
      throw new FileOpenFailedException(_filenameToOpen.value());
    }

  }

}
