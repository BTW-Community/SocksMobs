package net.minecraft.src;

public class SocksMobsModelBlockCauldron extends FCModelBlock
{
	private static final float onePX = 0.0625F;
	private static final float twoPX = 0.125F;
	private static final float threePX = onePX + twoPX;
	private static final float fourPX = 0.25F;
	private static final float fivePX = fourPX + onePX;
	private static final float sixPX = 0.375F;
	private static final float sevenPX = sixPX + onePX;
	private static final float eightPX = 0.5F;
	public static final float blockHeight = eightPX * 2;
	public static final float fullBlock = eightPX * 2;
    public AxisAlignedBB m_boxBase;
    public AxisAlignedBB m_boxSelection;

    protected void InitModel()
    {
        this.m_boxBase = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);
        //top left leg
        this.AddBox(0, 0, 0, twoPX, threePX, fourPX);
        this.AddBox(twoPX, 0, 0, fourPX, threePX, twoPX);
        //top right leg
        this.AddBox(fullBlock-fourPX, 0, 0, fullBlock-twoPX, threePX, twoPX);
        this.AddBox(fullBlock-twoPX, 0, 0, fullBlock, threePX, fourPX);
        //bottom left leg
        this.AddBox(0, 0, fullBlock-fourPX, twoPX, threePX, fullBlock);
        this.AddBox(twoPX, 0, fullBlock-twoPX, fourPX, threePX, fullBlock);
        //bottom right leg
        this.AddBox(fullBlock-fourPX, 0, fullBlock-twoPX, fullBlock-twoPX, threePX, fullBlock);
        this.AddBox(fullBlock-twoPX, 0, fullBlock-fourPX, fullBlock, threePX, fullBlock);
        //floor
        this.AddBox(0,threePX,0,fullBlock,fivePX,fullBlock);
        //walls
        this.AddBox(0, fivePX, 0, fullBlock-twoPX, blockHeight, twoPX);
        this.AddBox(twoPX, fivePX, fullBlock-twoPX, fullBlock, blockHeight, fullBlock);
        this.AddBox(0, fivePX, twoPX, twoPX, blockHeight, fullBlock);
        this.AddBox(fullBlock-twoPX, fivePX, 0, fullBlock, blockHeight, fullBlock-twoPX);
        
        
        
        this.m_boxSelection = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
    }
}
