struct IspBlockNoiseFilter
{
    uint8_t enable; /* min: 0, max: 1 */
    uint8_t coefTable[32U]; /* min: -5000, max: 5000 */
    uint8_t edgeMin; /* min: 0, max: 2147483647 */
    uint8_t edgeMax; /* min: 0, max: 2147483647 */
}
struct OtherFilter
{
    uint8_t enable; /* min: 0, max: 1 */
    uint8_t coefTable[2]; /* min: -5000, max: 5000 */
    uint8_t coefTable2[2]; /* min: 0, max: 2147483647 */
}
