package ca.phon.shell.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import ca.hedlund.jiss.ui.bindings.RunCommand;
import ca.phon.app.hooks.HookableAction;
import ca.phon.shell.PhonShellWindow;
import ca.phon.ui.CommonModuleFrame;

/**
 * Open a new PhonShell window for the current frame
 * and execute the specified script.
 *
 */
public class PhonShellScriptAction extends HookableAction {

	private final File scriptFile;
	
	public PhonShellScriptAction(File scriptFile) {
		this.scriptFile = scriptFile;
		
		putValue(NAME, scriptFile.getName());
		putValue(SHORT_DESCRIPTION, scriptFile.getAbsolutePath());
	}
	
	@Override
	public void hookableActionPerformed(ActionEvent ae) {
		PhonShellWindow window = null;
		
		CommonModuleFrame cmf = CommonModuleFrame.getCurrentFrame();
		if(cmf instanceof PhonShellWindow) {
			window = (PhonShellWindow)cmf;
		} else {
			window = new PhonShellWindow();
			window.pack();
			window.setVisible(true);
		}
		
		(new RunCommand(window.getConsole(), "::exec \"" + scriptFile.getAbsolutePath() + "\"")).runCommand();
	}

}
