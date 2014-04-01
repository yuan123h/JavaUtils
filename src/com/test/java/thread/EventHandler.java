package com.test.java.thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/**
 * Abstract base class for all HBase event handlers. Subclasses should implement
 * the {@link #process()} method. Subclasses should also do all necessary checks
 * up in their constructor if possible -- check table exists, is disabled, etc.
 * -- so they fail fast rather than later when process is running. Do it this
 * way because process be invoked directly but event handlers are also run in an
 * executor context -- i.e. asynchronously -- and in this case, exceptions
 * thrown at process time will not be seen by the invoker, not till we implement
 * a call-back mechanism so the client can pick them up later.
 * <p>
 * Event handlers have an {@link EventType}. {@link EventType} is a list of ALL
 * handler event types. We need to keep a full list in one place -- and as enums
 * is a good shorthand for an implemenations -- because event handlers can be
 * passed to executors when they are to be run asynchronously. The hbase
 * executor, see {@link ExecutorService}, has a switch for passing event type to
 * executor.
 * <p>
 * Event listeners can be installed and will be called pre- and post- process if
 * this EventHandler is run in a Thread (its a Runnable so if its {@link #run()}
 * method gets called). Implement {@link EventHandlerListener}s, and registering
 * using {@link #setListener(EventHandlerListener)}.
 * 
 * @see ExecutorService
 */
public abstract class EventHandler implements Runnable, Comparable<Runnable> {
    private static final Log LOG = LogFactory.getLog(EventHandler.class);
 
    // type of event this object represents
    protected EventType eventType;
 
    // sequence id generator for default FIFO ordering of events
    protected static AtomicLong seqids = new AtomicLong(0);
 
    // sequence id for this event
    private final long seqid;
 
    // Listener to call pre- and post- processing. May be null.
    private EventHandlerListener listener;
 
    // Time to wait for events to happen, should be kept short
    protected final int waitingTimeForEvents;
 
    /**
     * This interface provides pre- and post-process hooks for events.
     */
    public interface EventHandlerListener {
        /**
         * Called before any event is processed
         * 
         * @param event
         *            The event handler whose process method is about to be
         *            called.
         */
        public void beforeProcess(EventHandler event);
 
        /**
         * Called after any event is processed
         * 
         * @param event
         *            The event handler whose process method is about to be
         *            called.
         */
        public void afterProcess(EventHandler event);
    }
 
 
    static public class EventType {
         
        public String eventType;
        /**
         * Constructor
         */
        public EventType(String value) {
            this.eventType = value;
        }
 
         
    }
 
    /**
     * Default base class constructor.
     */
    public EventHandler(EventType eventType, int waitingTimeForEvents) {
        this.eventType = eventType;
        seqid = seqids.incrementAndGet();
        this.waitingTimeForEvents = waitingTimeForEvents;
    }
 
    public EventHandler(EventType eventType) {
        this.eventType = eventType;
        seqid = seqids.incrementAndGet();
        this.waitingTimeForEvents = 1000;
    }
 
    public void run() {
        try {
            if (getListener() != null)
                getListener().beforeProcess(this);
            process();
            if (getListener() != null)
                getListener().afterProcess(this);
        } catch (Throwable t) {
            LOG.error("Caught throwable while processing event " + eventType, t);
        }
    }
 
    /**
     * This method is the main processing loop to be implemented by the various
     * subclasses.
     * 
     * @throws IOException
     */
    public abstract void process() throws IOException;
 
    /**
     * Return the event type
     * 
     * @return The event type.
     */
    public EventType getEventType() {
        return this.eventType;
    }
 
    /**
     * Get the priority level for this handler instance. This uses natural
     * ordering so lower numbers are higher priority.
     * <p>
     * Lowest priority is Integer.MAX_VALUE. Highest priority is 0.
     * <p>
     * Subclasses should override this method to allow prioritizing handlers.
     * <p>
     * Handlers with the same priority are handled in FIFO order.
     * <p>
     * 
     * @return Integer.MAX_VALUE by default, override to set higher priorities
     */
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
 
    /**
     * @return This events' sequence id.
     */
    public long getSeqid() {
        return this.seqid;
    }
 
    /**
     * Default prioritized runnable comparator which implements a FIFO ordering.
     * <p>
     * Subclasses should not override this. Instead, if they want to implement
     * priority beyond FIFO, they should override {@link #getPriority()}.
     */
    @Override
    public int compareTo(Runnable o) {
        EventHandler eh = (EventHandler) o;
        if (getPriority() != eh.getPriority()) {
            return (getPriority() < eh.getPriority()) ? -1 : 1;
        }
        return (this.seqid < eh.seqid) ? -1 : 1;
    }
 
    /**
     * @return Current listener or null if none set.
     */
    public synchronized EventHandlerListener getListener() {
        return listener;
    }
 
    /**
     * @param listener
     *            Listener to call pre- and post- {@link #process()}.
     */
    public synchronized void setListener(EventHandlerListener listener) {
        this.listener = listener;
    }
 
    @Override
    public String toString() {
        return "Event #" + getSeqid() + " of type " + eventType + " ("
                + getInformativeName() + ")";
    }
 
    /**
     * Event implementations should override thie class to provide an
     * informative name about what event they are handling. For example,
     * event-specific information such as which region or server is being
     * processed should be included if possible.
     */
    public String getInformativeName() {
        return this.getClass().toString();
    }
}
 
 
 


 


 
 

 
 

 

 
