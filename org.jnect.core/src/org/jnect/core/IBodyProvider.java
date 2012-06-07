package org.jnect.core;

import org.jnect.bodymodel.Body;

public interface IBodyProvider {
	/**
	 * @return A body that is somehow made persistent. Returns <code>null</code> if the body provider does not provide
	 *         recording functionality. See also: {@link #canRecord()}.
	 */
	public Body getRecordingBody();

	/**
	 * @return A non persistent body. This never returns null.
	 */
	public Body getNonRecordingBody();

	/**
	 * Saves any recorded data if the body provider supports recording.
	 */
	public void save();

	/**
	 * @return
	 */
	public boolean canRecord();
}
